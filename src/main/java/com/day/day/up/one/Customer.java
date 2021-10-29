package com.day.day.up.one;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import java.util.Queue;

public class Customer implements Runnable{

    private Queue<Product> queue;  // 老王的货架

    private LockLocal laowang;   // 这是鱼摊老王

    private int buyNumber;   // 我要卖几条鱼


    public Customer(Queue queue,LockLocal manager,int buyNumber){
        this.queue=queue;
        this.laowang=manager;
        if(queue.size()<3){
            synchronized (manager){
              //  System.out.println("来了新顾客，老王招待完了以后，发现库存不足了，赶紧干活了");
                manager.notify();
            }
        }
        this.buyNumber=buyNumber;
    }
    private Product get(){
        if(queue.size()>=1){
            Product product= queue.poll();
            return product;
        }
       return null;
    }


    @Override
    public void run()  {
        int realNum=0;
        while (true){
            Product product=get();
            if(product!=null){
                realNum++;
                //Thread.sleep(20);
                buyNumber--;
            }else{
                System.out.println(Thread.currentThread().getName()+":尝试获取锁");

              //  System.out.println(VM.current().details());
                //打印对应的对象头信息
                System.out.println(ClassLayout.parseInstance(laowang).toPrintable());

                System.out.println("----分割付-");
               synchronized (laowang){

                   System.out.println(VM.current().details());
                   //打印对应的对象头信息
                   System.out.println("入锁后："+ClassLayout.parseInstance(laowang).toPrintable());


                 //  Thread.sleep(100000);
                    System.out.println(Thread.currentThread().getName()+":老王你这没货了，你倒是抓紧弄啊");
//                    laowang.notify();
//                    laowang.wait();
                }
            }
            if(buyNumber==0){
                System.out.println(Thread.currentThread().getName()+"买到了"+realNum+"条鱼，当前库存："+queue.size());
                break;
            }
        }

    }
}
