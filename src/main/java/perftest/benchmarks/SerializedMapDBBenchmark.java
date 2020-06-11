package perftest.benchmarks;

import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.DataInput2;
import org.mapdb.DataOutput2;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

import perftest.serial.SerializedKey;
import perftest.serial.SerializedValue;
import perftest.serial.Serializers;

public class SerializedMapDBBenchmark extends Benchmark {
    private HTreeMap<SerializedKey, SerializedValue> map;
    private DB db;
    private String filename;

    public SerializedMapDBBenchmark(String filename) {
        this.filename = filename;
    }

    @Override
    public void init() {
        db = DBMaker.fileDB(filename).fileMmapEnable().cleanerHackEnable().make();
        map = db.hashMap("map").keySerializer(new KeySerializer()).valueSerializer(new ValueSerializer()).createOrOpen();
    }

    @Override
    public void work() {
        SerializedKey key = new SerializedKey(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        map.put(key, new SerializedValue(key));
    }

    @Override
    public void teardown() {
        db.close();
    }

    static class KeySerializer implements Serializer<SerializedKey>, Serializable {
        private static final long serialVersionUID = 1L;

        @Override
        public void serialize(DataOutput2 out, SerializedKey value) throws IOException {
            out.write(Serializers.convertToBytes(value));
        }

        @Override
        public SerializedKey deserialize(DataInput2 input, int available) throws IOException {
            try {
                return (SerializedKey) Serializers.convertFromBytes(input.internalByteArray());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    static class ValueSerializer implements Serializer<SerializedValue>, Serializable {
        private static final long serialVersionUID = 1L;

        @Override
        public void serialize(DataOutput2 out, SerializedValue value) throws IOException {
            out.write(Serializers.convertToBytes(value));
        }

        @Override
        public SerializedValue deserialize(DataInput2 input, int available) throws IOException {
            try {
                return (SerializedValue) Serializers.convertFromBytes(input.internalByteArray());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}