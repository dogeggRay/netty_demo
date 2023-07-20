package org.netty.model.constant;

import io.netty.util.AttributeKey;
import org.netty.model.session.Session;

public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
