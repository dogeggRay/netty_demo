package org.netty.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.netty.common.util.IDUtil;
import org.netty.common.util.SessionUtil;
import org.netty.model.packet.request.LoginRequestPacket;
import org.netty.model.packet.response.LoginResponsePacket;
import org.netty.model.session.Session;
import org.netty.server.service.LoginService;

import java.util.Date;

@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

    private LoginRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginRequestPacket loginRequestPacket) throws Exception {
        System.out.println(new Date() + "收到客户端请求...");
        LoginResponsePacket response = new LoginResponsePacket();
        response.setVersion(loginRequestPacket.getVersion());
        if (valid(loginRequestPacket)) {
            System.out.println("[" + loginRequestPacket.getUserName() + "]登陆成功");

            String userId = IDUtil.randomId();
            response.setSuccess(true);
            response.setUserId(userId);
            response.setUserName(loginRequestPacket.getUserName());
            response.setMessage("登陆成功");
            SessionUtil.bindSession(new Session(userId, loginRequestPacket.getUserName()), channelHandlerContext.channel());
        } else {
            response.setSuccess(false);
            response.setMessage("账号密码校验失败");
        }

        channelHandlerContext.channel().writeAndFlush(response);
    }

    private boolean valid(LoginRequestPacket requestPacket) {
        return true;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        SessionUtil.unBindSession(ctx.channel());
    }
}
