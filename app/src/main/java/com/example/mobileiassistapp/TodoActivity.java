package com.example.mobileiassistapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TodoActivity extends AppCompatActivity {

    Button btnAdd;
    RecyclerView recyclerView;

    ArrayList<Todo> todoList;
    RecycleAdapter recycleAdapter;
    String uuid;
    LinearLayoutManager llm;
    FirebaseDatabase database;
    Button historyBtn;
    Button todoTargetBtn;
    TextView todoTargetPercentage;

    int isTargetActivity = 0;

    float todoListSize;

    float percentage;

    float filledperc;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        uuid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        btnAdd = (Button)findViewById(R.id.addBtn);
        recyclerView = (RecyclerView)findViewById((R.id.recyclerView));
        database = FirebaseDatabase.getInstance();
        historyBtn =(Button)findViewById(R.id.todohistoryBtn);
        todoTargetBtn = (Button) findViewById(R.id.todoTargetCalbtn);
        todoTargetPercentage =(TextView)findViewById(R.id.todoTitleView);

        todoTargetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isTargetActivity==0){



                    todoTargetBtn.setText("reset task calculation");
                    isTargetActivity =1;
                    todoListSize = todoList.size();
                    percentage = 100 / todoListSize;
                    filledperc = 0;

                    todoTargetPercentage.setText("TODO "+String.format("%.0f", filledperc) +"% COMPLETED");

                }
                else if( isTargetActivity == 1){

                  resetCalculation();
                }


            }
        });


        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetCalculation();
                Intent newIntent = new Intent(TodoActivity.this,ToDoHistoryActivity.class);
                TodoActivity.this.startActivity(newIntent);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetCalculation();
                Intent newIntent = new Intent(TodoActivity.this,ToDoSaveActivity.class);
                TodoActivity.this.startActivity(newIntent);

            }
        });


        todoList = new ArrayList<>();
        llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        recycleAdapter = new RecycleAdapter();
        recyclerView.setAdapter(recycleAdapter);

        recycleAdapter.notifyDataSetChanged();


    }

    @Override
    protected void onResume() {
        super.onResume();




        database.getReference("users").child(uuid).child("todoList").child("Active").addListenerForSingleValueEvent(

                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        todoList.clear();


                        for (DataSnapshot data : dataSnapshot.getChildren()) {

                            Todo todo = data.getValue(Todo.class);
                            todoList.add(todo);
                        }

                        recycleAdapter.notifyDataSetChanged();





                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

      public void resetCalculation(){

          todoTargetBtn.setText("start task calculation");
          todoTargetPercentage.setText("TODO");
          isTargetActivity =0;


      }



    private class RecycleAdapter extends RecyclerView.Adapter {


        @Override
        public int getItemCount() {

            return todoList.size();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false);
            SimpleItemViewHolder ivh = new SimpleItemViewHolder(v);
            return ivh;



        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            SimpleItemViewHolder viewHolder = (SimpleItemViewHolder) holder;
            viewHolder.position = position;
            Todo todo = todoList.get(position);
            ((SimpleItemViewHolder) holder).title.setText(todo.getName());
        }

        public final class SimpleItemViewHolder extends RecyclerView.ViewHolder {
            TextView title;
            Button deleteBtn;

            public int position;

            public SimpleItemViewHolder(View itemView) {
                super(itemView);
                title = (TextView) itemView.findViewById(R.id.itemTitle);
                deleteBtn = (Button)itemView.findViewById(R.id.deleteBtn);

                deleteBtn.setText("Complete");

                deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {





                        Todo todo = todoList.get(position);

                        DatabaseReference DR = FirebaseDatabase.getInstance().getReference("users").child(uuid).child("todoList").child("History").child(todo.getID());
                        DR.child("id").setValue(todo.getID());
                        DR.child("date").setValue(todo.getDate());
                        DR.child("message").setValue(todo.getMessage());
                        DR.child("name").setValue(todo.getName());


                        database.getReference("users").child(uuid).child("todoList").child("Active").child(todo.getID()).removeValue();

                        onResume();
                        Toast.makeText(getApplicationContext(), "Completed : "+todo.getName(), Toast.LENGTH_SHORT).show();

                        if(isTargetActivity == 1){

                            todoListSize = todoList.size();
                            filledperc = filledperc + percentage;
                            todoTargetPercentage.setText("TODO "+String.format("%.0f", filledperc) +"% COMPLETED");





                        }


                    }
                });



                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent newIntent = new Intent(TodoActivity.this, ToDoSaveActivity.class);
                        newIntent.putExtra("todo", todoList.get(position));
                        TodoActivity.this.startActivityForResult(newIntent,2);
                    }
                });


            }


        }


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==2)
        {
            String stat =data.getStringExtra("UpdateStat");
            if(stat == "1")
            {
                onResume();
            }
        }
    }


}