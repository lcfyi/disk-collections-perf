package perftest.serial;

import java.io.Serializable;

import org.apache.commons.lang3.SerializationUtils;

public class Serializers {
    public static byte[] convertToBytes(Serializable object) {
        if (object == null) {
            return null;
        }
        return SerializationUtils.serialize(object);
    }

    public static Object convertFromBytes(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        return SerializationUtils.deserialize(bytes);
    }
}