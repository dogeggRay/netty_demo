package org.netty.model.packet.response;

import lombok.Data;
import org.netty.model.command.Command;
import org.netty.model.packet.Packet;

@Data
public class LogoutResponsePacket extends Packet {

    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {

        return Command.LOGOUT_RESPONSE;
    }
}
