package perftest.benchmarks;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import net.openhft.chronicle.map.ChronicleMap;
import net.openhft.chronicle.map.ChronicleMapBuilder;
import perftest.serial.SerializedKey;
import perftest.serial.SerializedValue;

public class ChronicleMapBenchmark extends Benchmark {
    private ChronicleMap<SerializedKey, SerializedValue> map;
    private String filename;

    public ChronicleMapBenchmark(String filename) {
        this.filename = filename;
    }

    @Override
    public void init() {
        try {
            map = ChronicleMapBuilder.of(SerializedKey.class, SerializedValue.class).name(filename)
                    .averageKey(new SerializedKey(UUID.randomUUID().toString(), UUID.randomUUID().toString()))
                    .averageValue(new SerializedValue(
                            new SerializedKey(UUID.randomUUID().toString(), UUID.randomUUID().toString())))
                    .entries(RUN_ITERATIONS).createPersistedTo(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        map.close();
    }

}