package perftest.benchmarks.create;

import java.util.UUID;

import perftest.benchmarks.SerializedMapDBBase;
import perftest.serial.SerializedKey;
import perftest.serial.SerializedValue;

public class SerializedMapDBCreate extends SerializedMapDBBase {
    public SerializedMapDBCreate(String filename) {
        super(filename);
    }

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
        db.close();
    }
}