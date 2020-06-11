package perftest.benchmarks;

import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

public abstract class RocksDBBase extends Benchmark {
    protected String filename;
    protected Options options;
    protected RocksDB db;

    public RocksDBBase(String filename) {
        this.filename = filename;
        RocksDB.loadLibrary();
        options = new Options().setCreateIfMissing(true);
        try {
            db = RocksDB.open(options, filename);
        } catch (RocksDBException e) {
            e.printStackTrace();
        }
    }
}