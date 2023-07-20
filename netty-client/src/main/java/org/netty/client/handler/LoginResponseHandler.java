package org.netty.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.netty.common.util.SessionUtil;
import org.netty.model.packet.response.LoginResponsePacket;
import org.netty.model.session.Session;

import java.util.Date;

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + " channel连接成功");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginResponsePacket loginResponsePacket) throws Exception {

        String userId = loginResponsePacket.getUserId();
        String userName = loginResponsePacket.getUserName();

        if(loginResponsePacket.isSuccess()){
            System.out.println("客户端收到信息:登陆成功");
            System.out.println("[" + userName + "]登录成功，userId 为: " + userId);
            SessionUtil.bindSession(new Session(userId, userName), channelHandlerContext.channel());
        }else{
            System.out.println("客户端收到信息:登陆失败，原因:"+loginResponsePacket.getMessage());
        }
    }
}
