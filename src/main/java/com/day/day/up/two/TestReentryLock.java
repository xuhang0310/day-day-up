package com.day.day.up.two;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class TestReentryLock {

    public static void main(String[] args) {
        ReentrantLock lock=new ReentrantLock();

        AtomicInteger atomicInteger=new AtomicInteger();
       // lock.tryLock(1, TimeUnit.SECONDS);
    }
}
