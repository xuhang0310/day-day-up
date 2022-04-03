package com.day.webview.leecode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Solution {






    public int[] twoSum(int[] nums, int target) {
        int result [];
        HashMap<Integer,Integer> numberArray=new HashMap<Integer, Integer>(16);
        for(int i=0;i<nums.length;i++){
            if(numberArray.get(target-nums[i])!=null){
                result= new int[]{numberArray.get(target - nums[i]).intValue(), i};
                return result;
            };
            numberArray.put(nums[i],i);
            numberArray.put(nums[i],i);

        }
        return null;
    }

    public static void main(String args []){
        int array []={2,11,7,15};
        long start=System.currentTimeMillis();
        new Solution().twoSum(array,9);
        long end=System.currentTimeMillis();
       // System.out.println(end-start);
    }
}
