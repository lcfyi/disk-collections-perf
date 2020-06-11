package perftest.benchmarks.seqread;

import java.io.IOException;
import java.util.*;

import org.iq80.leveldb.DBException;

import perftest.benchmarks.LevelDBBase;
import perftest.serial.SerializedKey;
import perftest.serial.SerializedValue;
import perftest.serial.Serializers;

public class LevelDBSeqRead extends LevelDBBase {
    List<byte[]> keys;
    int idx;

    public LevelDBSeqRead(String filename) {
        super(filename);
        keys = new ArrayList<>();
        idx = 0;
    }

    @Override
    public void init() {
        for (int i = 0; i < RUN_ITERATIONS; i++) {
            SerializedKey key = new SerializedKey(UUID.randomUUID().toString(), UUID.randomUUID().toString());
            try {
                keys.add(Serializers.convertToBytes(key));
                db.put(Serializers.convertToBytes(key), Serializers.convertToBytes(new SerializedValue(key)));
            } catch (DBException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void work() {
        if (db.get(keys.get(idx)) == null) {
            throw new RuntimeException();
        }
        idx++;
    }

    @Override
    public void teardown() {
        try {
            String stats = db.getProperty("leveldb.stats");
            System.out.println(stats);
            db.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}