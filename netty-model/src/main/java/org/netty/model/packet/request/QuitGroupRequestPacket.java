package org.netty.model.packet.request;

import lombok.Data;
import org.netty.model.command.Command;
import org.netty.model.packet.Packet;

@Data
public class QuitGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {

        return Command.QUIT_GROUP_REQUEST;
    }
}