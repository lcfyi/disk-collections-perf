package perftest.benchmarks.seqread;

import java.util.*;

import perftest.benchmarks.CQEngineBase;
import perftest.serial.*;

import com.googlecode.cqengine.resultset.ResultSet;
import static com.googlecode.cqengine.query.QueryFactory.*;

public class CQEngineSeqRead extends CQEngineBase {
    List<SerializedKey> keys;
    int idx;

    public CQEngineSeqRead(String filename) {
        super(filename);
        keys = new ArrayList<>();
        idx = 0;
    }

    @Override
    public void init() {
        for (int i = 0; i < RUN_ITERATIONS; i++) {
            SerializedKey key = new SerializedKey(UUID.randomUUID().toString(), UUID.randomUUID().toString());
            SerializedValue value = new SerializedValue(key);
            keys.add(key);
            set.add(new SerializedSet(key, value));
        }
    }
    
    @Override
    public void work() {
        SerializedSet result = null;
        try (ResultSet<SerializedSet> results = set.retrieve(equal(SerializedSet.SET_ID, keys.get(idx).toString()))) {
            if (results.size() > 1) {
                throw new RuntimeException();
            }
            result = results.uniqueResult();
        }
        if (result == null) {
            throw new RuntimeException();
        }
        idx++;
    }

    @Override
    public void teardown() {
        System.out.print("Set size: ");
        System.out.println(set.size());
    }
}
