package com.day.day.up.one;

public class TestData {

    public static void main(String[] args) throws InterruptedException {
        int index=0;
        for(;;){

            index=(index+1)&15;
            System.out.println(index);
            Thread.sleep(100);
        }

    }
}
