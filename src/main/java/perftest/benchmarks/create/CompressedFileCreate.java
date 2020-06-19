package perftest.benchmarks.create;

import java.io.IOException;
import java.util.UUID;

import perftest.benchmarks.CompressedFileBase;
import perftest.serial.SerializedKey;
import perftest.serial.SerializedValue;

public class CompressedFileCreate extends CompressedFileBase {
    public CompressedFileCreate(String filename) {
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
        try {
            writer.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}