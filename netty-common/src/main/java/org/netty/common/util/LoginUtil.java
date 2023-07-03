package org.netty.common.util;

import io.netty.channel.Channel;
import io.netty.util.Attribute;
import org.netty.model.constant.Attributes;

public class LoginUtil {
    public static void markAsLogin(Channel channel){
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel){
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);
        return loginAttr != null;
    }
}
