package perftest.serial;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class SerializedValue implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String id1;
    private final String id2;
    private final String id3;
    private final String id4;
    private final SerializedKey key;

    public SerializedValue(SerializedKey key) {
        this.id1 = UUID.randomUUID().toString();
        this.id2 = UUID.randomUUID().toString();
        this.id3 = UUID.randomUUID().toString();
        this.id4 = UUID.randomUUID().toString();
        this.key = key;
    }

    @Override
    public String toString() {
        return key.getKey() + " " + this.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SerializedValue that = (SerializedValue) o;
        return Objects.equals(id1, that.id1) && Objects.equals(id2, that.id2) && Objects.equals(id3, that.id3)
                && Objects.equals(id4, that.id4);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id1, id2, id3, id4);
    }
}