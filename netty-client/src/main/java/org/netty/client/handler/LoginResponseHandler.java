package org.netty.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.netty.common.util.LoginUtil;
import org.netty.model.packet.LoginRequestPacket;
import org.netty.model.packet.LoginResponsePacket;

import java.util.Date;
import java.util.UUID;

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + " channel连接成功");
        LoginRequestPacket packet = new LoginRequestPacket();
        packet.setUserId(UUID.randomUUID().toString());
        packet.setPwd("32552147");
        packet.setUserName("raysvivi");

        ctx.channel().writeAndFlush(packet);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginResponsePacket loginResponsePacket) throws Exception {
        if(loginResponsePacket.isSuccess()){
            System.out.println("客户端收到信息:登陆成功");
            LoginUtil.markAsLogin(channelHandlerContext.channel());
        }else{
            System.out.println("客户端收到信息:登陆失败，原因:"+loginResponsePacket.getMessage());
        }
    }
}
