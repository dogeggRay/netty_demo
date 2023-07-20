package org.netty.model.packet.request;

import org.netty.model.command.Command;
import org.netty.model.packet.Packet;

public class LogoutRequestPacket extends Packet {
    @Override
    public Byte getCommand() {

        return Command.LOGOUT_REQUEST;
    }
}