package org.netty.model.packet;

import lombok.Data;
import org.netty.model.command.Command;

@Data
public class LoginRequestPacket extends Packet {
    private String userId;

    private String userName;

    private String pwd;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
