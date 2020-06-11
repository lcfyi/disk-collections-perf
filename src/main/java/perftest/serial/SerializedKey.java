package perftest.serial;

import java.io.Serializable;
import java.util.Objects;

public class SerializedKey implements Serializable {
    private static final long serialVersionUID = 1L;
    private String a;
    private String b;

    public SerializedKey(String a, String b) {
        this.a = a;
        this.b = b;
    }

    public String getKey() {
        return this.a + "." + this.b;
    }

    @Override
    public String toString() {
        return getKey();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SerializedKey that = (SerializedKey) o;
        return Objects.equals(a, that.a) && Objects.equals(b, that.b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}