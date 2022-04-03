package com.day.webview;

import com.day.webview.email.Email;
import com.day.webview.eth.EthResult;

import java.io.File;
import java.io.IOException;



public class GetMessage {





    public static Integer getCount(){
        try{
            String date= OkHttp3Util.doGet("https://api-cn.etherscan.com/api?module=account&action=tokentx&contractaddress=0xdac17f958d2ee523a2206206994597c13d831ec7&address=0xAbff5Fb38e4ED2DA3009c6b97A368ea2FB58F3C5&page=1&offset=100&sort=desc&startblock=14357773&endblock=99999999&");
            System.out.println(date);

            EthResult result= GsonUtil.json2Bean(date, EthResult.class);
            System.out.println(result.getResult().size());

            return result.getResult().size();
        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }

    public static void main(String[] args) throws IOException {
        File file=new File("/Users/xupei/test-fra");
        if(file.exists()){
            file.createNewFile();
        }




        while (true){
            Integer count=getCount();
            if(count>1){
                try{
                    Email.sendEmail();

                }catch (Exception e){

                }

            }
            try{
                Thread.sleep(1000*30*2);
            }catch (Exception e){

            }



        }

    }


}
