package perftest.benchmarks.seqread;

import java.util.*;

import perftest.benchmarks.MapBase;
import perftest.serial.SerializedKey;

public class MapSeqRead extends MapBase {
    List<SerializedKey> keys;
    int idx;

    public MapSeqRead() {
        keys = new ArrayList<>();
        idx = 0;
    }

    @Override
    public void init() {
        for (int i = 0; i < RUN_ITERATIONS; i++) {
            SerializedKey key = new SerializedKey(UUID.randomUUID().toString(), UUID.randomUUID().toString());
            keys.add(key);
            map.put(key, generateMap());
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
    }
}