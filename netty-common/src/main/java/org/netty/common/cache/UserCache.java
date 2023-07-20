package org.netty.common.cache;

public class UserCache {
    private static final ThreadLocal<String> USER_ID_CACHE = new ThreadLocal<>();

    public static void setUserCache(String userId){
        USER_ID_CACHE.set(userId);
    }

    public static String getUserCache(){
        return USER_ID_CACHE.get();
    }

    public static void clearUserCache(){
        USER_ID_CACHE.remove();
    }
}
