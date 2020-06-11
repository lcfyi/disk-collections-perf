package perftest.benchmarks.randomread;

import java.io.IOException;
import java.util.*;

import org.rocksdb.RocksDBException;

import perftest.benchmarks.RocksDBBase;
import perftest.serial.SerializedKey;
import perftest.serial.SerializedValue;
import perftest.serial.Serializers;

public class RocksDBRandomRead extends RocksDBBase {
    List<byte[]> keys;

    public RocksDBRandomRead(String filename) {
        super(filename);
        keys = new ArrayList<>();
    }

    @Override
    public void init() {
        for (int i = 0; i < RUN_ITERATIONS; i++) {
            SerializedKey key = new SerializedKey(UUID.randomUUID().toString(), UUID.randomUUID().toString());
            try {
                byte[] b = Serializers.convertToBytes(key);
                keys.add(b);
                db.put(b, Serializers.convertToBytes(new SerializedValue(key)));
            } catch (RocksDBException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void work() {
        try {
            if (db.get(keys.get((int) (Math.random() * RUN_ITERATIONS))) == null) {
                throw new RuntimeException();
            }
        } catch (RocksDBException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void teardown() {
        db.close();
    }    
}
