package org.netty.model.packet;

import lombok.Data;
import org.netty.model.command.Command;

@Data
public class LoginResponsePacket extends Packet{

    private boolean success;

    private String message;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
