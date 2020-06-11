package perftest.benchmarks.seqread;

import java.util.*;

import perftest.benchmarks.MapDBBase;
import perftest.serial.SerializedKey;
import perftest.serial.SerializedValue;

public class MapDBSeqRead extends MapDBBase {
    List<SerializedKey> keys;
    int idx;

    public MapDBSeqRead(String filename) {
        super(filename);
        keys = new ArrayList<>();
        idx = 0;
    }

    @Override
    public void init() {
        for (int i = 0; i < RUN_ITERATIONS; i++) {
            SerializedKey key = new SerializedKey(UUID.randomUUID().toString(), UUID.randomUUID().toString());
            keys.add(key);
            map.put(key, new SerializedValue(key));
        }
    }

    @Override
    public void work() {
        if (map.get(keys.get(idx)) == null) {
            throw new RuntimeException();
        }
        idx++;
    }

    @Override
    public void teardown() {
        System.out.print("Map size: ");
        System.out.println(map.size());
        db.close();
    }
}