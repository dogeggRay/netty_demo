package org.netty.model.packet.response;

import lombok.Data;
import org.netty.model.command.Command;
import org.netty.model.packet.Packet;

@Data
public class LoginResponsePacket extends Packet {

    private String userId;

    private String userName;
    private boolean success;

    private String message;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
