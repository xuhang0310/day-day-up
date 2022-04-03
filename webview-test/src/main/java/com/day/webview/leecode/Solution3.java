package com.day.webview.leecode;

import java.util.HashMap;

public class Solution3 {

    public int lengthOfLongestSubstring(String s) {

        // 记录字符上一次出现的位置
        if (s.length()==0) {
            return 0;
        }
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max = 0;
        int left = 0;
        for(int i = 0; i < s.length(); i ++){
            if(map.containsKey(s.charAt(i))){
                //System.out.println(s.charAt(i)+"----current------"+left+"------"+i);
                left = Math.max(left,map.get(s.charAt(i)) + 1);
                //System.out.println(left+"----left------"+max+"----"+(s.charAt(i)+1));
            }
            map.put(s.charAt(i),i);
            max = Math.max(max,i-left+1);
           // System.out.println(left+"----left------"+max);
        }
        return max;
    }

    public static void main(String[] args) {
        String s="adc";
        int index=s.charAt(1);

        int b=Math.max(0,1);
        System.out.println(b);
        System.out.println(index);
        new Solution3().lengthOfLongestSubstring("abbc");
    }
}
