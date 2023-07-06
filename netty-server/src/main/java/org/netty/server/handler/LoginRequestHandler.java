package org.netty.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.netty.model.packet.*;
import org.netty.server.service.LoginService;

import java.util.Date;

public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    private LoginService loginService;

    public LoginRequestHandler(){
        loginService = new LoginService();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginRequestPacket loginRequestPacket) throws Exception {
        System.out.println(new Date() + "收到客户端请求...");
        LoginResponsePacket response = new LoginResponsePacket();
        response.setVersion(loginRequestPacket.getVersion());
        if(loginService.valid(loginRequestPacket)){
            System.out.println("登陆成功");
            response.setSuccess(true);
            response.setMessage("登陆成功");
        }else{
            response.setSuccess(false);
            response.setMessage("账号密码校验失败");
        }

        channelHandlerContext.channel().writeAndFlush(response);
    }
}
