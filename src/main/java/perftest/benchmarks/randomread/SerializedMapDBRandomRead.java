package perftest.benchmarks.randomread;

import java.util.*;

import perftest.benchmarks.SerializedMapDBBase;
import perftest.serial.SerializedKey;

public class SerializedMapDBRandomRead extends SerializedMapDBBase {
    List<SerializedKey> keys;

    public SerializedMapDBRandomRead(String filename) {
        super(filename);
        keys = new ArrayList<>();
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
        if (map.get(keys.get((int)(Math.random() * RUN_ITERATIONS))) == null) {
            throw new RuntimeException();
        }
    }

    @Override
    public void teardown() {
        System.out.print("Map size: ");
        System.out.println(map.size());
        db.close();
    }
}
