package perftest.benchmarks.create;

import java.util.UUID;

import perftest.benchmarks.CQEngineBase;
import perftest.serial.*;

public class CQEngineCreate extends CQEngineBase {
    public CQEngineCreate(String filename) {
        super(filename);
    }

    @Override
    public void init() {
    }
    
    @Override
    public void work() {
        SerializedKey key = new SerializedKey(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        SerializedValue value = new SerializedValue(key);
        set.add(new SerializedSet(key, value));
    }

    @Override
    public void teardown() {
        System.out.print("Set size: ");
        System.out.println(set.size());
    }
}