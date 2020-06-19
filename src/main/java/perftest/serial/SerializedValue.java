package perftest.serial;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SerializedValue implements Serializable {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final long serialVersionUID = 1L;

    public static final String ID1_KEY = "id1";
    private String id1 = null;
    private String id2 = null;
    private String id3 = null;
    private String id4 = null;

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
    @JsonSubTypes({
        @JsonSubTypes.Type(value = SerializedKey.class, name = "type")
    })
    private SerializedKey key = null;

    private SerializedValue() {
    }

    public SerializedValue(SerializedKey key) {
        this.id1 = UUID.randomUUID().toString();
        this.id2 = UUID.randomUUID().toString();
        this.id3 = UUID.randomUUID().toString();
        this.id4 = UUID.randomUUID().toString();
        this.key = key;
    }

    public SerializedValue(String id1, String id2, String id3, String id4, SerializedKey key) {
        this.id1 = id1;
        this.id2 = id2;
        this.id3 = id3;
        this.id4 = id4;
        this.key = key;
    }

    public String getid1() {
        return id1;
    }

    public String getid2() {
        return id2;
    }

    public String getid3() {
        return id3;
    }

    public String getid4() {
        return id4;
    }

    public SerializedKey getKey() {
        return key;
    }

    @Override
    public String toString() {
        try {
            return MAPPER.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialized SerializedValue object", e);
        }
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