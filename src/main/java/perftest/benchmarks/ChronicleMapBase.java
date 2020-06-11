package perftest.benchmarks;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import net.openhft.chronicle.map.ChronicleMap;
import net.openhft.chronicle.map.ChronicleMapBuilder;
import perftest.serial.SerializedKey;
import perftest.serial.SerializedValue;

public abstract class ChronicleMapBase extends Benchmark {
    protected ChronicleMap<SerializedKey, SerializedValue> map;
    protected String filename;

    public ChronicleMapBase(String filename) {
        this.filename = filename;
        try {
            map = ChronicleMapBuilder.of(SerializedKey.class, SerializedValue.class).name(this.filename)
                    .averageKey(new SerializedKey(UUID.randomUUID().toString(), UUID.randomUUID().toString()))
                    .averageValue(new SerializedValue(
                            new SerializedKey(UUID.randomUUID().toString(), UUID.randomUUID().toString())))
                    .entries(RUN_ITERATIONS).createPersistedTo(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}