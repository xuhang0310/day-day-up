package com.day.day.up.one;

import lombok.SneakyThrows;

import java.util.Queue;

public class Producer implements Runnable{

    private LockLocal laowang;

    private Queue<Product> queue;

    private int tag=0;

    public Producer(Queue queue,LockLocal manager){
        this.queue=queue;
        this.laowang = manager;
    }


    private void produce()  {
        if(queue.size()<SuperMarket.max){
            tag++;

            Product product=new Product("fish"+tag);
            System.out.println("加紧生产鱼肉中："+product.getName());
            queue.add(product);
        }

    }


    @Override
    public void run() {
        while (true){
            produce();
            if(queue.size()>=10){

                synchronized (laowang){
                    System.out.println("库存已经满了，我要休息了,当前库存："+queue.size());
                    laowang.notifyAll();
                    //laowang.wait();
                }

            }

        }

    }
}
