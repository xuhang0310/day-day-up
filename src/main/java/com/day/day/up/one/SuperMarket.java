package com.day.day.up.one;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;


public class SuperMarket {

    public static Integer max=10;

    public static boolean isFull=false;

    public static boolean isEmpty=true;

    private Queue<Product> queue=new LinkedBlockingDeque<Product>(max);


    private Producer producer;

    private Customer customer;

    public SuperMarket(){
        producer=new Producer(queue);
        customer=new Customer(queue);
    }

    public static void main(String[] args) {
            SuperMarket superMarket=new SuperMarket();

            new Thread(superMarket.producer).start();
            for(int i=0;i<100;i++){
                new Thread(superMarket.customer,"顾客"+i).start();
            }



    }
}
