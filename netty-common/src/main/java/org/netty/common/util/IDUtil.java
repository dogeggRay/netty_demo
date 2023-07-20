package org.netty.common.util;

import java.util.UUID;

public class IDUtil {
    public static String randomId() {
        return String.join("", UUID.randomUUID().toString().split("-"));
    }
}
