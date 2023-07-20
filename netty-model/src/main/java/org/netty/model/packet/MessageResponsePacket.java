package org.netty.model.packet;

import lombok.Data;
import org.netty.model.command.Command;

@Data
public class MessageResponsePacket  extends Packet{

    private String fromUserId;

    private String fromUserName;
    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }
}
