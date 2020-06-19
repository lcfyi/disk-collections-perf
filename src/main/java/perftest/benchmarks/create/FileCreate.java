package perftest.benchmarks.create;

import java.io.IOException;
import java.util.UUID;

import perftest.benchmarks.FileBase;
import perftest.serial.SerializedKey;
import perftest.serial.SerializedValue;

public class FileCreate extends FileBase {
    public FileCreate(String filename) {
        super(filename);
    }

    @Override
    public void init() {
    }

    @Override
    public void work() {
        SerializedKey key = new SerializedKey(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        try {
            writer.write(new SerializedValue(key).toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void teardown() {
    }
}