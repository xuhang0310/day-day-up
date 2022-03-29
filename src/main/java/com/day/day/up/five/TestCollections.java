package com.day.day.up.five;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TestCollections {

    static final Unsafe unsafe = getUnsafe();
    static final boolean is64bit = true;

    public static void main(String[] args) throws InterruptedException {

        List<String> list=new ArrayList();
        String abc="1";
        System.out.println(abc.hashCode());
        String abc1="12";
        System.out.println(abc1.hashCode());
        String abc2="13";
        System.out.println(abc2.hashCode());
        String abc3="14";
        System.out.println(abc3.hashCode());
        String abc4="15";
        System.out.println(abc4.hashCode());
        list.add(abc); list.add(abc1); list.add(abc2); list.add(abc3); list.add(abc4);
        List<String> list1=new ArrayList();
        list1.addAll(list);

        System.out.println("------复制数组开始----");
        for(int i=0;i<5;i++){
         String temp=list1.get(i);
            System.out.println(temp.hashCode());
            if(temp.hashCode()==49){
                System.out.println(abc.hashCode());
                printAddresses("address",abc);
                printAddresses("address",temp);
                System.out.println((abc==temp)+":对象");
                temp="1ddf";
            }

              list1.add(temp);
        }
        System.out.println("------复制数组结束----");
        System.out.println("------复制数组赋值后打印开始----");
        for(int i=0;i<5;i++){
            String temp=list1.get(i);
            System.out.println(temp);
            //System.out.println(temp.hashCode());
            if(temp.hashCode()==49){

                System.out.println(abc.hashCode());
                printAddresses("address",abc);
                printAddresses("address",temp);
                System.out.println((abc==temp)+":对象");
                temp=new String("1ddf");
            }

            //list1.add(temp);
        }
        System.out.println("------复制数组赋值后打印开始----");

        for(int i=0;i<list.size();i++){
            String temp=list.get(i);
            System.out.println("hashcode:"+temp.hashCode());
            System.out.println(temp);
        }
       // Thread.dumpStack();

       // Thread.sleep(100000);
    }


    public static void printAddresses(String label, Object... objects) {
        System.out.print(label + ": 0x");
        long last = 0;
        int offset = unsafe.arrayBaseOffset(objects.getClass());
        int scale = unsafe.arrayIndexScale(objects.getClass());
        switch (scale) {
            case 4:
                long factor = is64bit ? 8 : 1;
                final long i1 = (unsafe.getInt(objects, offset) & 0xFFFFFFFFL) * factor;
                System.out.print(Long.toHexString(i1));
                last = i1;
                for (int i = 1; i < objects.length; i++) {
                    final long i2 = (unsafe.getInt(objects, offset + i * 4) & 0xFFFFFFFFL) * factor;
                    if (i2 > last)
                        System.out.print(", +" + Long.toHexString(i2 - last));
                    else
                        System.out.print(", -" + Long.toHexString( last - i2));
                    last = i2;
                }
                break;
            case 8:
                throw new AssertionError("Not supported");
        }
        System.out.println();
    }

    private static Unsafe getUnsafe() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            return (Unsafe) theUnsafe.get(null);
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }


}
