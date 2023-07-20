package org.netty.common.command;

import io.netty.channel.Channel;
import org.netty.model.packet.request.CreateGroupRequestPacket;

import java.util.Arrays;
import java.util.Scanner;

public class CreateGroupConsoleCommand implements ConsoleCommand{
    private static final String USER_ID_SPLITER = ",";
    @Override
    public void exec(Scanner scanner, Channel channel) {
        CreateGroupRequestPacket packet = new CreateGroupRequestPacket();

        System.out.print("【拉人群聊】输入userId列表,userId之间英文逗号隔开:");
        String userId = scanner.next();
        packet.setUserIdList(Arrays.asList(userId.split(USER_ID_SPLITER)));
        channel.writeAndFlush(packet);
    }
}
