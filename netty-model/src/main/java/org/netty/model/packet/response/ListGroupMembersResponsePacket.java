package org.netty.model.packet.response;

import lombok.Data;
import org.netty.model.command.Command;
import org.netty.model.packet.Packet;
import org.netty.model.session.Session;

import java.util.List;

@Data
public class ListGroupMembersResponsePacket extends Packet {

    private String groupId;

    private List<Session> sessionList;

    @Override
    public Byte getCommand() {

        return Command.LIST_GROUP_MEMBERS_RESPONSE;
    }
}