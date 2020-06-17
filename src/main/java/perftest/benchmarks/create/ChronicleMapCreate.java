package perftest.benchmarks.create;

import java.util.UUID;

import perftest.benchmarks.ChronicleMapBase;
import perftest.serial.SerializedKey;

public class ChronicleMapCreate extends ChronicleMapBase {    
    public ChronicleMapCreate(String filename) {
        super(filename);
    }

    @Override
    public void init() {
    }

    @Override
    public void work() {
        SerializedKey key = new SerializedKey(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        map.put(key, generateMap());
    }

    @Override
    public void teardown() {
        System.out.print("Map size: ");
        System.out.println(map.size());
        map.close();
    }

}