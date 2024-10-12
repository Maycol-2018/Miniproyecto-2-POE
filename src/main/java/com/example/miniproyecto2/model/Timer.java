package com.example.miniproyecto2.model;

public class Timer implements ITamer{
    private Timer timer;
    private int minutes;
    private int seconds;

    @Override
    public void startTime(){
        updateTime();
        updateLabel();
    }
    @Override
    public void updateTime(){

    }

    @Override
    public void updateLabel() {

    }


}
