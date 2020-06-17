package perftest.benchmarks.seqread;

import java.util.*;

import perftest.benchmarks.ChronicleMapBase;
import perftest.serial.SerializedKey;

public class ChronicleMapSeqRead extends ChronicleMapBase {
    List<SerializedKey> keys;
    int idx;

    public ChronicleMapSeqRead(String filename) {
        super(filename);
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
        map.close();
    }

}