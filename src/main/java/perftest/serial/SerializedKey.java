package perftest.serial;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    @JsonCreator
    public static SerializedKey fromString(@JsonProperty("key") final String key) {
        System.out.println(key);
        String[] v = key.split("\\.");
        return new SerializedKey(v[0], v[1]);
    }
}