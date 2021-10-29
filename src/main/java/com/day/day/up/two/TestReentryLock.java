package com.day.day.up.two;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestReentryLock {

    public static void main(String[] args) {
        final ReentrantReadWriteLock lock=new ReentrantReadWriteLock(true);
        final int index=0;
        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.writeLock().lock();

                System.out.println(Thread.currentThread().getName()+":当前线程"+index);
             //   lock.writeLock().unlock();
            }
        },"333333").start();
    //    for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    lock.readLock().lock();

                    System.out.println(Thread.currentThread().getName()+":当前线程"+index);
                    //lock.readLock().unlock();
                }
            },"11111").start();


    //    }

       // lock.tryLock(1, TimeUnit.SECONDS);
    }
}
