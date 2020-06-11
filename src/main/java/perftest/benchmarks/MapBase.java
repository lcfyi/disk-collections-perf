package perftest.benchmarks;

import java.util.concurrent.*;

import perftest.benchmarks.Benchmark;
import perftest.serial.SerializedKey;
import perftest.serial.SerializedValue;

public abstract class MapBase extends Benchmark {
    protected ConcurrentMap<SerializedKey, SerializedValue> map;

    public MapBase() {
        map = new ConcurrentHashMap<>();
    }
}