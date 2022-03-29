package com.day.day.up.eight;

public class TestSuanfa {

    public static int array []={0,1,2,4,4,5,5,5,5,5,5,5,5,6,7,8};

    private static int count=0;

    private static int loopTime=0;

    public static void name(){
        name();
    }

    public static int getNumber(int number,int size){
        loopTime++;
        if(size==0){
            return count;
        }
        if(size>array.length){
            return count;
        }
        int middle=size/2;
       int temp= array[middle];
       if(temp==number){
           count++;
           searchUp(middle,number);
           searchDown(middle,number);
       }
       if(temp>number){
           getNumber(number,middle);
       }
       if(temp<number){
           getNumber(number,middle+middle/2);
       }
       return count;
    }




    public static void searchUp(int middle,int number){
        int tempIndex=middle-1;
        if(tempIndex<0){
            return;
        }
        int temp= array[tempIndex];
        if(temp==number){
            loopTime++;
            count++;
            searchUp(tempIndex,number);
        }
    }

    public static void searchDown(int middle,int number){
        int tempIndex=middle+1;
        if(tempIndex>array.length){
            return;
        }
        int temp= array[tempIndex];
        if(temp==number){
            loopTime++;
            count++;
            searchDown(tempIndex,number);
        }
    }


    public static void main(String[] args) {
        int count=getNumber(100,array.length);

        System.out.println(count);
        System.out.println(loopTime);

    }
}
