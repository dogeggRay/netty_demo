package org.netty.model.serializer;

public interface Serializer {

    /**
     * 序列化算法
     * @return
     */
    byte getSerializerAlgorithm();

    /**
     * Java对象转换成二进制数据
     * @param object
     * @return
     */
    byte[] serialize(Object object);

    /**
     * 二进制数据转成JAVA对象
     * @param clazz
     * @param bytes
     * @return
     * @param <T>
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);

    Serializer DEFAULT = new JSONSerializer();
}
