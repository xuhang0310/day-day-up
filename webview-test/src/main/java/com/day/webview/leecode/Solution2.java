package com.day.webview.leecode;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;

public class Solution2 {

    public static void main(String[] args) {
      long time=  LocalDateTime.now().atZone(ZoneId.of("GMT+8")).toInstant().toEpochMilli();
        System.out.println(time);
    }

    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        HashMap<Integer,ListNode> resultList=new HashMap();
        ListNode current;
        int jinwei=0;
        int index=0;
        while(l1!=null||l2!=null){
            int number= l1.val+l2.val+jinwei;
            jinwei=0;
            if(number>=10){
                number=number-10;
                jinwei=1;
            }
            current=new ListNode();
            current.val=number;
            if(index>0){
                resultList.get(index-1).next=current;
            }
            resultList.put(index,current);
            index++;
            l1=l1.next;
            l2=l2.next;
            if(l1==null&&l2==null){
                if(jinwei==1){
                    current=new ListNode();
                    current.val=jinwei;
                    resultList.get(index-1).next=current;
                }
                break;
            }
            if(l1==null){
                l1=new ListNode(0,null);
            }
            if(l2==null){
                l2=new ListNode(0,null);
            }
        }
        return resultList.get(0);
    }

    private ListNode test(ArrayList<ListNode> list1,ArrayList<ListNode> list2){
        int remain=0;
        HashMap<Integer,ListNode> resultList=new HashMap();
        for(int i=0;i<list1.size();i++){
            int powNumber= (int) Math.pow(10,i);
            int number= list1.get(i).val*powNumber+remain;
            if(list2.size()<i){
                number+=list2.get(i).val*powNumber;
            }
            remain=number-powNumber;
            ListNode current=new ListNode();
            current.val=number;
            if(resultList.get(i)!=null){
                resultList.get(i).next=current;
            };
            resultList.put(i,current);

        }
        return resultList.get(0);
    }







    public class ListNode {

         int val;
         ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }


    }
}
