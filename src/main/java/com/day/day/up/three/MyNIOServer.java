package com.day.day.up.three;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class MyNIOServer {


    public static void main(String[] args) throws Exception{

        //创建ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //得到一个Selecor对象
        Selector selector = Selector.open();
        //绑定端口8888
        serverSocketChannel.socket().bind(new InetSocketAddress(8888));
        //非阻塞
        serverSocketChannel.configureBlocking(false);
        //把 serverSocketChannel注册到selector只关心OP_ACCEPT事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        serverSocketChannel.register(selector, SelectionKey.OP_READ);
        serverSocketChannel.register(selector, SelectionKey.OP_CONNECT);
        serverSocketChannel.register(selector, SelectionKey.OP_WRITE);
        while (true) {
            if(selector.select() == 0) {
                System.out.println("无连接");
                continue;
            }
            //返回触发事件的集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            System.out.println("selectionKeys size = " + selectionKeys.size());
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()) {
                //获取到SelectionKey
                SelectionKey key = keyIterator.next();
                //根据key 对应的通道发生的事件做相应处理
                if(key.isAcceptable()) { //如果是 OP_ACCEPT, 有新的客户端连接
                    //该该客户端生成一个 SocketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println("客户端连接成功 生成了一个 socketChannel " + socketChannel.hashCode());
                    //将  SocketChannel 设置为非阻塞
                    socketChannel.configureBlocking(false);
                    //将socketChannel 注册到selector, 关注事件为 OP_READ， 同时给socketChannel关联一个Buffer
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    System.out.println("客户端连接后 ，注册的selectionkey 数量=" + selector.keys().size());
                }
                //发生 OP_READ
                if(key.isReadable()) {
                    //通过key 反向获取到对应channel
                    SocketChannel channel = (SocketChannel)key.channel();
                    //获取到该channel关联的buffer

                    ByteBuffer buffer = (ByteBuffer)key.attachment();
                    channel.read(buffer);
                    buffer.clear();
                    System.out.println("form 客户端 " + new String(buffer.array()));
                }
                //手动从集合中移动当前的selectionKey, 防止重复操作
                keyIterator.remove();
            }
        }
    }

}
