package com.example.mobileiassistapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
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

public class ToDoHistoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button ClearBtn;
    RecycleAdapter adapter;
    ArrayList<Todo> todoList;
    FirebaseDatabase database;
    LinearLayoutManager llm;
    AlertDialog.Builder alertDialogBuilder;
    String uuid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_history);

        uuid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        database = FirebaseDatabase.getInstance();
        ClearBtn =(Button)findViewById(R.id.cleartodohisBtn);
        ClearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertDialogBuilder.setMessage("Are you sure to clear all items from history?");
                alertDialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                                database.getReference("users").child(uuid).child("todoList").child("History").removeValue();
                                onResume();

                                Toast.makeText(getApplicationContext(), "All items removed!", Toast.LENGTH_SHORT).show();



                            }
                        });

                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();










            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewTodoHis);

        todoList = new ArrayList<>();
        llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);

        adapter = new RecycleAdapter();
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();




    }

    @Override
    protected void onResume() {
        super.onResume();

        alertDialogBuilder = new AlertDialog.Builder(this);

        database.getReference("users").child(uuid).child("todoList").child("History").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        todoList.clear();


                        for (DataSnapshot data : dataSnapshot.getChildren()) {

                            Todo todo = data.getValue(Todo.class);
                            todoList.add(todo);
                        }

                        adapter.notifyDataSetChanged();





                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    private class RecycleAdapter extends RecyclerView.Adapter {


        @Override
        public int getItemCount() {
            return todoList.size();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false);
            SimpleItemViewHolder pvh = new SimpleItemViewHolder(v);
            return pvh;
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


                deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Todo todo = todoList.get(position);

                        alertDialogBuilder.setMessage("Are you sure to delete this ToDo permanently?");
                        alertDialogBuilder.setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {

                                        database.getReference("users").child(uuid).child("todoList").child("History").child(todo.getID()).removeValue();

                                        onResume();
                                        Toast.makeText(getApplicationContext(), "Removed : "+todo.getName(), Toast.LENGTH_SHORT).show();



                                    }
                                });

                        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });

                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();





                    }
                });
                itemView.setOnClickListener(new View.OnClickListener() {



                    @Override
                    public void onClick(View view) {

                        final Todo todo = todoList.get(position);
                        alertDialogBuilder.setMessage("Do you want to add this ToDo back to the list?");
                        alertDialogBuilder.setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {

                                        DatabaseReference DR = FirebaseDatabase.getInstance().getReference("users").child(uuid).child("todoList").child("Active").child(todo.getID());
                                        DR.child("id").setValue(todo.getID());
                                        DR.child("date").setValue(todo.getDate());
                                        DR.child("message").setValue(todo.getMessage());
                                        DR.child("name").setValue(todo.getName());



                                        database.getReference("users").child(uuid).child("todoList").child("History").child(todo.getID()).removeValue();
                                        onResume();



                                    }
                                });

                        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });

                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();

                    }
                });

            }


        }


    }




}
