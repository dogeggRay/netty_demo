package org.netty.model.packet.response;

import org.netty.model.command.Command;
import org.netty.model.packet.Packet;

public class HeartBeatResponsePacket extends Packet {
    @Override
    public Byte getCommand() {
        return Command.HEARTBEAT_RESPONSE;
    }
}