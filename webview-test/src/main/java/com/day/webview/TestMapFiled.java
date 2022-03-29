package com.day.webview;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.day.webview.bean.VideoFingerPrint;
import com.day.webview.bean.XulieHuaUtil;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Map;
import java.util.Set;

public class TestMapFiled {

    private static String fileName="/Users/xupei/Desktop/record.json";
    public static String  readJson() {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws IOException {


        String data=readJson();

       File file=new File("/Users/xupei/record-channel1");
       if(!file.exists()){
           file.createNewFile();
       }

       FileChannel fileChannel=new RandomAccessFile(file,"rw").getChannel();

       MappedByteBuffer mappedByteBuffer=fileChannel.map(FileChannel.MapMode.READ_WRITE,0,1024*40*1024);


        JSONObject jobj = JSON.parseObject(data);
        Set<Map.Entry<String, Object>> setJsonObject= jobj.entrySet();
        for(Map.Entry<String, Object> key:setJsonObject){
            JSONArray jsonArray= (JSONArray) key.getValue();
            VideoFingerPrint image=new VideoFingerPrint();
            image.setVideoId(key.getKey());
            boolean bool=false;
            for(int i=0;i<jsonArray.size();i++){
                JSONArray jsonArrayIndex= (JSONArray) jsonArray.get(i);
                byte [] imageByte=new byte[jsonArrayIndex.size()];
                for(int j=0;j<jsonArrayIndex.size();j++){
                    Integer number= (Integer) jsonArrayIndex.get(j);
                    imageByte[j]=intToByteArray(number)[3];
                }
                image.getImageByte().add(imageByte);
            }
            byte [] dataByte=XulieHuaUtil.ObjectToByte(image);
            mappedByteBuffer.putInt(dataByte.length);
            mappedByteBuffer.put(dataByte);

        }
        System.out.println(mappedByteBuffer.position()/1024/1024);


    }

    public static byte[] intToByteArray(int i) {
        byte[] result = new byte[4];
        result[0] = (byte)((i >> 24) & 0xFF);
        result[1] = (byte)((i >> 16) & 0xFF);
        result[2] = (byte)((i >> 8) & 0xFF);
        result[3] = (byte)(i & 0xFF);
        return result;
    }
}
