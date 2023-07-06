package org.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.netty.client.handler.LoginResponseHandler;
import org.netty.client.handler.MessageResponseHandler;
import org.netty.common.util.LoginUtil;
import org.netty.common.util.PackageEncoder;
import org.netty.common.util.PacketDecoder;
import org.netty.model.packet.MessageRequestPacket;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ClientEntry {
    private static final int MAX_RETRY = 10;
    private static int retry = 10;
    public static void main(String[] args) {
        retry = 10;
        NioEventLoopGroup worker = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(worker)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new PacketDecoder());
                        socketChannel.pipeline().addLast(new LoginResponseHandler());
                        socketChannel.pipeline().addLast(new MessageResponseHandler());
                        socketChannel.pipeline().addLast(new PackageEncoder());
                    }
                });
        connect(bootstrap,"localhost",8084);
    }

    private static void connect(Bootstrap bootstrap,String ip,int port){
        bootstrap.connect(ip,port).addListener(future -> {
            if(future.isSuccess()){
                Channel channel = ((ChannelFuture)future).channel();
                startConsoleThread(channel);
            }else if(retry==0){
                System.out.println("重试次数用完，放弃重连");
            }else{
                int order = MAX_RETRY-retry+1;
                retry--;
                int delay = 1 << order;
                System.out.println("连接失败，第"+order+"次重连....");
                bootstrap.config().group().schedule(() -> connect(bootstrap,ip,port),delay, TimeUnit.SECONDS);
            }
        });

    }

    private static void startConsoleThread(Channel channel){
        new Thread(() -> {
           while(!Thread.interrupted()){
               if(LoginUtil.hasLogin(channel)){
                   Scanner sc = new Scanner(System.in);
                   String line = sc.nextLine();

                   System.out.println("开始发送消息至服务端："+line);
                   MessageRequestPacket mrp = new MessageRequestPacket();
                   mrp.setMessage(line);
                   channel.writeAndFlush(mrp);
               }
           }
        }).start();
    }
}