package org.netty.model.packet.response;

import lombok.Data;
import org.netty.model.command.Command;
import org.netty.model.packet.Packet;

@Data
public class JoinGroupResponsePacket extends Packet {
    private String groupId;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {

        return Command.JOIN_GROUP_RESPONSE;
    }
}