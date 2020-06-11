package perftest.benchmarks.create;

import java.io.IOException;
import java.util.UUID;

import perftest.benchmarks.LevelDBBase;
import perftest.serial.SerializedKey;
import perftest.serial.SerializedValue;
import perftest.serial.Serializers;

public class LevelDBCreate extends LevelDBBase {

    public LevelDBCreate(String filename) {
        super(filename);
    }

    @Override
    public void init() {
    }

    @Override
    public void work() {
        SerializedKey key = new SerializedKey(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        db.put(Serializers.convertToBytes(key), Serializers.convertToBytes(new SerializedValue(key)));
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