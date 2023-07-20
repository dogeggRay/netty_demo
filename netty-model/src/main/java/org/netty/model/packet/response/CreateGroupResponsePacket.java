package org.netty.model.packet.response;

import lombok.Data;
import org.netty.model.command.Command;
import org.netty.model.packet.Packet;

import java.util.List;

@Data
public class CreateGroupResponsePacket extends Packet {
    private boolean success;

    private String groupId;

    private List<String> userNameList;

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_RESPONSE;
    }
}
