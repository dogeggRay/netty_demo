package org.netty.model.packet;

import lombok.Data;
import org.netty.model.command.Command;

@Data
public class MessageRequestPacket extends Packet{

    private String toUserId;

    private String userName;
    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }

    public MessageRequestPacket(String toUserId, String message){
        this.toUserId = toUserId;
        this.message = message;
    }
}
