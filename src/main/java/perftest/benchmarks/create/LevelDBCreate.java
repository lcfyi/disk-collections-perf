package perftest.benchmarks.create;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.iq80.leveldb.DB;
import org.iq80.leveldb.Options;

import perftest.benchmarks.Benchmark;
import perftest.benchmarks.LevelDBBase;
import perftest.serial.SerializedKey;
import perftest.serial.SerializedValue;
import perftest.serial.Serializers;

import static org.fusesource.leveldbjni.JniDBFactory.*;

public class LevelDBCreate extends LevelDBBase {

    public LevelDBCreate(String filename) {
        super(filename);
    }

    // public static void test() {
    // Options options = new Options();
    // options.createIfMissing(true);
    // DB db = factory.open(new File("./data.dat"), options);
    // SerializedKey key = new SerializedKey("asdf", "sdf");
    // try {
    // db.put(Serializer.convertToBytes(key), Serializer.convertToBytes(new
    // SerializedValue(key)));
    // System.out.println((SerializedValue)Serializer.convertFromBytes(db.get(Serializer.convertToBytes(key))));
    // } finally {
    // db.close();
    // }
    // }
    @Override
    public void init() {
    }

    @Override
    public void work() {
        try {
            SerializedKey key = new SerializedKey(UUID.randomUUID().toString(), UUID.randomUUID().toString());
            db.put(Serializers.convertToBytes(key), Serializers.convertToBytes(new SerializedValue(key)));
        } catch (IOException e) {
            System.out.println(e);
        }
    }


    @Override
    public void teardown() {
        try {
            String stats = db.getProperty("leveldb.stats");
            System.out.println(stats);
            db.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}