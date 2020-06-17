package perftest.benchmarks;

import java.util.concurrent.*;
import java.util.*;

import perftest.benchmarks.Benchmark;
import perftest.serial.SerializedKey;
import perftest.serial.SerializedValue;

public abstract class MapBase extends Benchmark {
    protected ConcurrentMap<SerializedKey, Map<SerializedKey, SerializedValue>> map;

    public MapBase() {
        map = new ConcurrentHashMap<>();
    }
}