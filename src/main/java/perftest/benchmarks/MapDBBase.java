package perftest.benchmarks;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;

public abstract class MapDBBase extends Benchmark {
    protected HTreeMap map;
    protected DB db;
    protected String filename;

    public MapDBBase(String filename) {
        this.filename = filename;
        db = DBMaker.fileDB(this.filename).fileMmapEnable().cleanerHackEnable().make();
        map = db.hashMap("map").createOrOpen();
    }
}