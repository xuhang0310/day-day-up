package com.day.day.up.one;

import lombok.SneakyThrows;

import java.util.Queue;

public class Producer implements Runnable{

    private Queue<Product> queue;

    private int index=0;

    public Producer(Queue queue){
        this.queue=queue;
    }


    private void produce(){
        if(queue.size()<SuperMarket.max){

            index++;
            Product product=new Product("fish"+index);
            System.out.println("--生产数据--"+product.getName());
            queue.add(product);
        }

    }

    @SneakyThrows
    @Override
    public void run() {
        while (SuperMarket.isEmpty){
            produce();
            Thread.sleep(100);
        }

    }
}
