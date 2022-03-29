package com.day.day.up.five;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class TestString {

    static final Unsafe unsafe = getUnsafe();
    static final boolean is64bit = true;

    public static void main(String[] args) {
        String abc="111";
        String abc1="111";
        String d="1";
        String dd="11";
        String ddd=d+dd;
        String ddd1=new String(d+dd);
        System.out.println(abc==ddd);
        System.out.println(abc==ddd1);
        System.out.println(abc.hashCode());
        System.out.println(ddd.hashCode());
        System.out.println(ddd1.hashCode());

        printAddresses("ssss",abc);
        printAddresses("abc1",abc1);
        printAddresses("dddd",ddd);
        printAddresses("dddd1",ddd1);
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
