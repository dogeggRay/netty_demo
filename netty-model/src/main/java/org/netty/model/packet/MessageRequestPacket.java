package org.netty.model.packet;

import lombok.Data;
import org.netty.model.command.Command;

@Data
public class MessageRequestPacket extends Packet{

    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
