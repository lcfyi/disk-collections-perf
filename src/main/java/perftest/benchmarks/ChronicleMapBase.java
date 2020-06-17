package perftest.benchmarks;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import net.openhft.chronicle.map.ChronicleMap;
import net.openhft.chronicle.map.ChronicleMapBuilder;
import perftest.serial.SerializedKey;
import perftest.serial.SerializedValue;

public abstract class ChronicleMapBase extends Benchmark {
    protected ChronicleMap<SerializedKey, Map<SerializedKey, SerializedValue>> map;
    protected String filename;

    public ChronicleMapBase(String filename) {
        this.filename = filename;
        try {
            map = ChronicleMapBuilder.of(SerializedKey.class, (Class<Map<SerializedKey, SerializedValue>>) (Class) Map.class).name(this.filename)
                    .averageKey(new SerializedKey(UUID.randomUUID().toString(), UUID.randomUUID().toString()))
                    .averageValueSize(128_000)
                    .entries(RUN_ITERATIONS).createPersistedTo(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}