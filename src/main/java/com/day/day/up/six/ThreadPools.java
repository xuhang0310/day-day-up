package com.day.day.up.six;

import lombok.SneakyThrows;

import java.util.concurrent.*;

public class ThreadPools {
    private static ExecutorService pool;
    public static void main(String[] args) {
        pool = new ThreadPoolExecutor(1, 10, 1000,
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(10),
                Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
        for(int i=0;i<20;i++) {
           // System.out.println(i);
            pool.execute(new ThreadTask());

        }
        new Thread(new PrintTask(pool)).start();
        System.out.println("111");

    }

    public static class PrintTask implements Runnable{
        private ExecutorService pool;

        public PrintTask(ExecutorService pool){
            this.pool=pool;
        }
        @Override
        public void run() {
            while (true){
                try{
                    Thread.sleep(1000);
                }catch (Exception e){

                }
                System.out.println(pool);
            }
        }
    }


    public static class ThreadTask implements Runnable{

        public ThreadTask() {

        }


        public void run() {
            try{
                Thread.sleep(10000);
            }catch (Exception e){

            }

            System.out.println(Thread.currentThread().getName());
        }
    }
}
