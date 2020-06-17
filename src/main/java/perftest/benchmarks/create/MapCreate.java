package perftest.benchmarks.create;

import java.util.UUID;

import perftest.benchmarks.MapBase;
import perftest.serial.SerializedKey;

public class MapCreate extends MapBase {
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
    }
}