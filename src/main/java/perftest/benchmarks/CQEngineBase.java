package perftest.benchmarks;

import java.io.File;
import java.util.Objects;

import com.googlecode.cqengine.ConcurrentIndexedCollection;
import com.googlecode.cqengine.IndexedCollection;
import com.googlecode.cqengine.attribute.SimpleAttribute;
import com.googlecode.cqengine.persistence.composite.CompositePersistence;
import com.googlecode.cqengine.persistence.disk.DiskPersistence;
import com.googlecode.cqengine.persistence.onheap.OnHeapPersistence;
import com.googlecode.cqengine.query.option.QueryOptions;

import perftest.serial.*;

public abstract class CQEngineBase extends Benchmark {
    public static class SerializedSet {
        public SerializedKey k;
        public SerializedValue v;

        public SerializedSet(SerializedKey k, SerializedValue v) {
            this.k = k;
            this.v = v;
        }

        @Override
        public String toString() {
            return "SerializedSet{" + k + ", " + v + "};";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            SerializedSet that = (SerializedSet) o;
            return Objects.equals(k, that.k);
        }

        @Override
        public int hashCode() {
            return Objects.hash(k);
        }

        // Attributes
        public static final SimpleAttribute<SerializedSet, String> SET_ID = new SimpleAttribute<SerializedSet, String>("keyId") {
            public String getValue(SerializedSet set, QueryOptions queryOptions) { return set.k.toString(); }
        };
    }

    protected IndexedCollection<SerializedSet> set;

    public CQEngineBase(String filename) {
        DiskPersistence<SerializedSet, String> persistence = DiskPersistence.onPrimaryKeyInFile(SerializedSet.SET_ID, new File(filename));
        set = new ConcurrentIndexedCollection<SerializedSet>(CompositePersistence.of(
            persistence,
            OnHeapPersistence.onPrimaryKey(SerializedSet.SET_ID)
        ));
    }
}
