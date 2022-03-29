package com.day.webview.press;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class TestMmap {

    public static void write(int size,byte [] data) throws IOException {
        String path="/Users/xupei/record-mmap-"+size;

        File file=new File(path);
        if(!file.exists()){
            file.createNewFile();
        }

        FileChannel fileChannel=new RandomAccessFile(file,"rw").getChannel();

        MappedByteBuffer mappedByteBuffer=fileChannel.map(FileChannel.MapMode.READ_WRITE,0,1024*size*2);



        long start=System.currentTimeMillis();
        for(int i=0;i<size;i++){
            mappedByteBuffer.putInt(data.length);
            mappedByteBuffer.put(data);
        }
        long end=System.currentTimeMillis();

        System.out.println("mmap:"+(end-start));
    }
}
