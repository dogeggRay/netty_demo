package org.netty.model.packet.request;

import lombok.Data;
import org.netty.model.command.Command;
import org.netty.model.packet.Packet;

import java.util.List;

@Data
public class CreateGroupRequestPacket extends Packet {
    private List<String> userIdList;

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_REQUEST;
    }
}
