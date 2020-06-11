package perftest.benchmarks;

import java.io.File;
import java.io.IOException;

import org.iq80.leveldb.DB;
import org.iq80.leveldb.Options;

import perftest.benchmarks.Benchmark;

import static org.fusesource.leveldbjni.JniDBFactory.*;

public abstract class LevelDBBase extends Benchmark {

    protected Options options;
    protected DB db;
    protected String filename;

    public LevelDBBase(String filename) {
        this.filename = filename;
        options = new Options();
        options.createIfMissing(true);
        try {
            db = factory.open(new File(filename), options);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}