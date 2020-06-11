package perftest.benchmarks.randomread;

import java.util.*;

import perftest.benchmarks.MapBase;
import perftest.serial.SerializedKey;
import perftest.serial.SerializedValue;

public class MapRandomRead extends MapBase {
    List<SerializedKey> keys;

    public MapRandomRead() {
        keys = new ArrayList<>();
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
        if (map.get(keys.get((int)(Math.random() * RUN_ITERATIONS))) == null) {
            throw new RuntimeException();
        }
    }

    @Override
    public void teardown() {
        System.out.print("Map size: ");
        System.out.println(map.size());
    }
}