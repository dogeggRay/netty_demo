package org.netty.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.netty.model.packet.LoginRequestPacket;
import org.netty.model.packet.LoginResponsePacket;
import org.netty.model.packet.Packet;
import org.netty.model.utils.PacketCodeC;
import org.netty.server.service.LoginService;

import java.nio.charset.Charset;
import java.util.Date;

public class SLoginHandler extends ChannelInboundHandlerAdapter {
    private LoginService loginService;

    public SLoginHandler(){
        loginService = new LoginService();
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf message = (ByteBuf)msg;
        System.out.println(new Date() + " 服务端读到数据："+ message.toString(Charset.forName("UTF-8")));

        Packet packet = PacketCodeC.INSTANCE.decode(message);

        if(packet instanceof LoginRequestPacket){
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket)packet;
            LoginResponsePacket response = new LoginResponsePacket();
            response.setVersion(packet.getVersion());
            if(loginService.valid(loginRequestPacket)){
                response.setSuccess(true);
                response.setMessage("登陆成功");
            }else{
                response.setSuccess(false);
                response.setMessage("账号密码校验失败");
            }

            ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(response);
            ctx.channel().writeAndFlush(responseByteBuf);
        }
    }
}
