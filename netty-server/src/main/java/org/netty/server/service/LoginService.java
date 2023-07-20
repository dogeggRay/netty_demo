package org.netty.server.service;

import org.netty.model.packet.request.LoginRequestPacket;

public class LoginService {
    public boolean valid(LoginRequestPacket loginRequestPacket){
        return true;
    }

}
