package com.day.day.up.two;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;

import java.util.concurrent.locks.ReentrantLock;

public class TestSelinte {

    private static String resource="resource";
    public static void main(String[] args) throws InterruptedException {
        final ReentrantLock lock=new ReentrantLock(true);


        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
            }
        }).start();


        Thread.sleep(100);


    }
}
