package org.netty.common.command;

import io.netty.channel.Channel;
import org.netty.common.util.SessionUtil;
import org.netty.model.packet.request.LogoutRequestPacket;

import java.util.Scanner;

public class LogoutConsoleCommand implements ConsoleCommand{
    @Override
    public void exec(Scanner scanner, Channel channel) {
        if(!SessionUtil.hasLogin(channel)){
           System.out.println("请勿重复登出");
        }
        LogoutRequestPacket logoutRequestPacket = new LogoutRequestPacket();
        channel.writeAndFlush(logoutRequestPacket);
    }
}
