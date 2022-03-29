package com.day.day.up.seven;

public class TestVolidate {



    private volatile  int number=0;

    public void setNumber(){
        number++;
    }

    public int getNumber(){
        return number;
    }

    public static void main(String args []){
        new Thread(new Runnable() {
            @Override
            public void run() {
                TestVolidate testVolidate=new TestVolidate();
                testVolidate.setNumber();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                TestVolidate testVolidate=new TestVolidate();
                testVolidate.getNumber();
            }
        }).start();

    }
}
