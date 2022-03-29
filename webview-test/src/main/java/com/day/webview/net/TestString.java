package com.day.webview.net;

public class TestString {

    public static void main(String[] args) {
//        String str="dog";
//        String str2="dog";
//        String str3="d";
//        String str4="og";
//        String str5=str3+str4;
//        String str1=new String("dog");
//        System.out.println(str==str1);
//        System.out.println(str==str2);
//        System.out.println(str==str5);

        String str1="45";
        String str3 = new String("4") + new String("5");
        System.out.println(str3 == str3.intern()); // false
        System.out.println(str3 == str1);
    }
}
