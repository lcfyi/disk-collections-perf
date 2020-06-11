package perftest.benchmarks;

import java.util.UUID;
import java.util.concurrent.*;

import perftest.serial.SerializedKey;
import perftest.serial.SerializedValue;

public class MapBenchmark extends Benchmark {
    private ConcurrentMap<SerializedKey, SerializedValue> map = new ConcurrentHashMap<>();

    @Override
    public void init() {
    }

    @Override
    public void work() {
        SerializedKey key = new SerializedKey(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        map.put(key, new SerializedValue(key));
    }

    @Override
    public void teardown() {
        System.out.print("Map size: ");
        System.out.println(map.size());
    }
}