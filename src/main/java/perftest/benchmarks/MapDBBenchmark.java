package perftest.benchmarks;

import java.util.UUID;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;

import perftest.serial.SerializedKey;
import perftest.serial.SerializedValue;

public class MapDBBenchmark extends Benchmark {
    private HTreeMap map;
    private DB db;
    private String filename;

    public MapDBBenchmark(String filename) {
        this.filename = filename;
    }

    @Override
    public void init() {
        db = DBMaker.fileDB(filename).fileMmapEnable().cleanerHackEnable().make();
        map = db.hashMap("map").createOrOpen();
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
}