package perftest.benchmarks.create;

import java.util.UUID;

import org.rocksdb.RocksDBException;

import perftest.benchmarks.RocksDBBase;
import perftest.serial.SerializedKey;
import perftest.serial.SerializedValue;
import perftest.serial.Serializers;

public class RocksDBCreate extends RocksDBBase {

    public RocksDBCreate(String filename) {
        super(filename);
    }

    @Override
    public void init() {
    }

    @Override
    public void work() {
        SerializedKey key = new SerializedKey(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        try {
            db.put(Serializers.convertToBytes(key), Serializers.convertToBytes(new SerializedValue(key)));
        } catch (RocksDBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void teardown() {
        db.close();
    }    
}
