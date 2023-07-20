package org.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.netty.common.handler.IMIdleStateHandler;
import org.netty.common.util.PacketCodecHandler;
import org.netty.common.util.Spliter;
import org.netty.server.handler.*;

public class ServerEntry {
    public static void main(String[] args) {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        ServerBootstrap server = new ServerBootstrap();
                        server.group(boss,worker)
                                .channel(NioServerSocketChannel.class)
                                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                                    @Override
                                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                                        System.out.println("initChannel Access");
                                        nioSocketChannel.pipeline().addLast(new IMIdleStateHandler());
                                        nioSocketChannel.pipeline().addLast(new Spliter());
                                        nioSocketChannel.pipeline().addLast(new PacketCodecHandler());
                                        nioSocketChannel.pipeline().addLast(LoginRequestHandler.INSTANCE);
                                        nioSocketChannel.pipeline().addLast(HeartBeatRequestHandler.INSTANCE);
                                        nioSocketChannel.pipeline().addLast(new AuthHandler());
                                        nioSocketChannel.pipeline().addLast(new MessageRequestHandler());
                                        nioSocketChannel.pipeline().addLast(new CreateGroupRequestHandler());
                                        nioSocketChannel.pipeline().addLast(new JoinGroupRequestHandler());
                                        // 退群请求处理器
                                        nioSocketChannel.pipeline().addLast(new QuitGroupRequestHandler());
                                        // 获取群成员请求处理器
                                        nioSocketChannel.pipeline().addLast(new ListGroupMembersRequestHandler());
                                        nioSocketChannel.pipeline().addLast(new GroupMessageRequestHandler());
                        nioSocketChannel.pipeline().addLast(new LogoutRequestHandler());

                    }
                }).bind(8084);
    }
}