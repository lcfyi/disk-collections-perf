package perftest.benchmarks;

import java.util.*;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

import perftest.serial.SerializedKey;
import perftest.serial.SerializedValue;

public abstract class SerializedMapDBBase extends Benchmark {
    protected HTreeMap<SerializedKey, Map<SerializedKey, SerializedValue>> map;
    protected DB db;
    protected String filename;

    public SerializedMapDBBase(String filename) {
        this.filename = filename;
        db = DBMaker.fileDB(this.filename).fileMmapEnable().cleanerHackEnable().make();
        map = db.<SerializedKey, Map<SerializedKey, SerializedValue>>hashMap("map").keySerializer(Serializer.JAVA).valueSerializer(Serializer.JAVA).createOrOpen();
    }
}