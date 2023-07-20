package org.netty.model.packet.request;

import lombok.Data;
import org.netty.model.command.Command;
import org.netty.model.packet.Packet;

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
