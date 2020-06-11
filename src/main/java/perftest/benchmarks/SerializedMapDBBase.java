package perftest.benchmarks;

import java.io.IOException;
import java.io.Serializable;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.DataInput2;
import org.mapdb.DataOutput2;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

import perftest.serial.SerializedKey;
import perftest.serial.SerializedValue;
import perftest.serial.Serializers;

public abstract class SerializedMapDBBase extends Benchmark {
    protected HTreeMap<SerializedKey, SerializedValue> map;
    protected DB db;
    protected String filename;

    public SerializedMapDBBase(String filename) {
        this.filename = filename;
        db = DBMaker.fileDB(this.filename).fileMmapEnable().cleanerHackEnable().make();
        map = db.hashMap("map").keySerializer(new KeySerializer()).valueSerializer(new ValueSerializer()).createOrOpen();
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

        @Override
        public int fixedSize() {
            return -1;
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

        @Override
        public int fixedSize() {
            return -1;
        }
    }

}