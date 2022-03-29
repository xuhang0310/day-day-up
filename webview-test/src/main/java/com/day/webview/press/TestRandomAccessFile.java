package com.day.webview.press;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class TestRandomAccessFile {

    public static void write(int size,byte [] data) throws IOException {
        String path="/Users/xupei/record-channel-"+size;
        RandomAccessFile file=new RandomAccessFile(new File(path),"rw");

        long start=System.currentTimeMillis();
        for(int i=0;i<size;i++){
            file.write(data.length);
            file.write(data);
        }
        long end=System.currentTimeMillis();

        System.out.println("random:"+(end-start));
    }

    public static void main(String[] args) throws IOException {
        int size=1204;
        write(size,load());
        TestMmap.write(size,load());
    }

    public static byte [] load(){
        byte [] data=new byte[1024];
        byte num=1;
        for(int i=0;i<data.length;i++){
            data[i]=num;
        }
        return data;
    }
}
