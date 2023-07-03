package org.netty.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.netty.common.util.LoginUtil;
import org.netty.model.packet.LoginRequestPacket;
import org.netty.model.packet.LoginResponsePacket;
import org.netty.model.packet.Packet;
import org.netty.model.utils.PacketCodeC;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.UUID;

public class DemoHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + " 客户端写出数据");
        LoginRequestPacket packet = new LoginRequestPacket();
        packet.setUserId(UUID.randomUUID().toString());
        packet.setPwd("32552147");
        packet.setUserName("raysvivi");

        ByteBuf buffer = PacketCodeC.INSTANCE.encode(packet);
        ctx.channel().writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf responseBB = (ByteBuf) msg;
        Packet packet = PacketCodeC.INSTANCE.decode(responseBB);
        if(packet instanceof LoginResponsePacket){
            LoginResponsePacket repsonse = (LoginResponsePacket)packet;
            if(repsonse.isSuccess()){
                LoginUtil.markAsLogin(ctx.channel());
                System.out.println("客户端收到信息:登陆成功");
            }else{
                System.out.println("客户端收到信息:登陆失败，原因:"+repsonse.getMessage());
            }

        }
    }
}
