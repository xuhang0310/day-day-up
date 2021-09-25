package com.day.day.up.one;

import java.util.Queue;

public class Customer implements Runnable{

    private Queue<Product> queue;


    public Customer(Queue queue){
        this.queue=queue;
    }
    private Product get(){
        if(queue.size()>=1){
            Product product= queue.poll();
            return product;
        }
       return null;
    }

    @lombok.SneakyThrows
    @Override
    public void run() {
        while (true){
            Product product=get();
            if(product!=null){
                System.out.println(Thread.currentThread().getName()+"买到了一条鱼："+product.getName()+"，当前库存："+queue.size());
                break;
            }
            Thread.sleep(500);
        }

    }
}
