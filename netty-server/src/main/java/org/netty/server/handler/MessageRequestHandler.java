package org.netty.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.netty.common.util.SessionUtil;
import org.netty.model.packet.MessageRequestPacket;
import org.netty.model.packet.MessageResponsePacket;
import org.netty.model.session.Session;

import java.util.Date;

public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageRequestPacket messageRequestPacket) throws Exception {
        System.out.println(new Date() + "收到客户端消息："+ messageRequestPacket.getMessage());

        Session session = SessionUtil.getSession(channelHandlerContext.channel());

        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setMessage(messageRequestPacket.getMessage());
        messageResponsePacket.setFromUserName(session.getUserName());
        messageResponsePacket.setFromUserId(messageRequestPacket.getToUserId());

        Channel toUserChannel = SessionUtil.getChannel(messageRequestPacket.getToUserId());
        if(toUserChannel!=null && SessionUtil.hasLogin(toUserChannel)){
            toUserChannel.writeAndFlush(messageResponsePacket);
        }else{
            System.out.println("【"+ messageRequestPacket.getToUserId() +"】不在线 发送失败！");
        }

    }
}
