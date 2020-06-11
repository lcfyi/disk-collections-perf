package perftest.benchmarks.seqread;

import java.util.*;

import org.rocksdb.RocksDBException;

import perftest.benchmarks.RocksDBBase;
import perftest.serial.SerializedKey;
import perftest.serial.SerializedValue;
import perftest.serial.Serializers;

public class RocksDBSeqRead extends RocksDBBase {
    List<byte[]> keys;
    int idx;

    public RocksDBSeqRead(String filename) {
        super(filename);
        keys = new ArrayList<>();
        idx = 0;
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
            if (db.get(keys.get(idx)) == null) {
                throw new RuntimeException();
            }
        } catch (RocksDBException e1) {
            e1.printStackTrace();
        }
        idx++;
    }

    @Override
    public void teardown() {
        db.close();
    }    
}
