package com.day.day.up.one;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;


public class SuperMarket {

    private LockLocal laowang =new LockLocal();   //这是入驻的商家，鱼摊老板老王
    public static Integer max=10;
    private Queue<Product> queue=new LinkedBlockingDeque<Product>(max);   //老王的货架
    private Producer producer;
    public SuperMarket(){
        producer=new Producer(queue, laowang);
    }

    public static void main(String[] args) throws InterruptedException {
            SuperMarket superMarket=new SuperMarket();

            new Thread(superMarket.producer).start();
            for(int i=0;i<50;i++){
                new Thread(new Customer(superMarket.queue,superMarket.laowang,3),"顾客"+i).start();
            }
    }
}
