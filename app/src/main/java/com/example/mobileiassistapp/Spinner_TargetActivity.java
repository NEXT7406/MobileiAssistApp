package com.example.mobileiassistapp;

public class Spinner_TargetActivity {
    private String tgrade;
    private int tgrademarks;

    public Spinner_TargetActivity(){}

    public Spinner_TargetActivity(String tgrade, int tgrademarks) {
        this.tgrade = tgrade;
        this.tgrademarks = tgrademarks;
    }

    public String getTgrade() {
        return tgrade;
    }

    public void setTgrade(String tgrade) {
        this.tgrade = tgrade;
    }

    public int getTgrademarks() {
        return tgrademarks;
    }

    public void setTgrademarks(int tgrademarks) {
        this.tgrademarks = tgrademarks;
    }

    @Override
    public String toString() {
        return tgrade;
    }
}
