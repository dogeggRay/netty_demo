package org.netty.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.netty.model.packet.MessageRequestPacket;
import org.netty.model.packet.MessageResponsePacket;
import org.netty.model.utils.PacketCodeC;

import java.util.Date;

public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageRequestPacket messageRequestPacket) throws Exception {
        System.out.println(new Date() + "收到客户端消息："+ messageRequestPacket.getMessage());

        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setMessage("服务端回复【"+messageRequestPacket.getMessage()+"】");

        channelHandlerContext.channel().writeAndFlush(messageResponsePacket);
    }
}
