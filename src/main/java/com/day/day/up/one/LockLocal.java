package com.day.day.up.one;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public class LockLocal {

    private int  abc=0;

    private int abc1=1;

    private Long uuu=90L;


    public static void main(String[] args) {

        LockLocal lockLocal=new LockLocal();
     //   System.out.println(VM.current().details());
        //打印对应的对象头信息
     //   System.out.println( ClassLayout.parseInstance(lockLocal).toPrintable());

        lockLocal.abc=44;

        lockLocal.hashCode();

        System.out.println( ClassLayout.parseInstance(lockLocal).toPrintable());
        synchronized (lockLocal){
            System.out.println("---locking--");
            System.out.println( ClassLayout.parseInstance(lockLocal).toPrintable());
        }

        System.out.println("---after--");
        System.out.println( ClassLayout.parseInstance(lockLocal).toPrintable());

    }



}
