package perftest.benchmarks;

import java.io.File;
import java.io.IOException;

import org.iq80.leveldb.DB;
import org.iq80.leveldb.Options;
import static org.fusesource.leveldbjni.JniDBFactory.*;

public class LevelDBBenchmark extends Benchmark {

    private Options options = new Options();
    private DB db;
    public String filename;

    public LevelDBBenchmark(String filename) {
        this.filename = filename;
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
        try {
            options.createIfMissing(true);
            db = factory.open(new File(this.filename), options);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void work() {
        // try {
        // // db.put(Serializer.convertToBytes())
        // } catch (IOException e) {
        // System.out.println(e);
        // }
    }


    @Override
    public void teardown() {
        try {
            db.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}