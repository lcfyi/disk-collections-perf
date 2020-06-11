package perftest;

import java.io.File;
import java.io.IOException;

import perftest.benchmarks.*;

public final class Main {
    private static String TMP_DIR = "tmp";
    private static String CHRONICLE_DB = TMP_DIR + "/chronicle.dat";
    private static String MAP_DB = TMP_DIR + "/mapdb.dat";
    private static String SERIALIZED_MAP_DB = TMP_DIR + "/smapdb.dat";
    private static String LEVEL_DB = TMP_DIR + "/leveldb.dat";

    public static void cleanTemporaryDatabases(File f) throws IOException {
        if (f.getName().contains(".gitkeep")) {
            return;
        }
        if (f.isDirectory()) {
            for (File c : f.listFiles()) {
                cleanTemporaryDatabases(c);
            }
        } else {
            f.delete();
        }
    }

    public static void main(String[] args) throws IOException {
        cleanTemporaryDatabases(new File(TMP_DIR));
        Benchmark mapBenchmark = new MapBenchmark();
        mapBenchmark.run();
        System.out.println(mapBenchmark);
        Benchmark cmBenchmark = new ChronicleMapBenchmark(CHRONICLE_DB);
        cmBenchmark.run();
        System.out.println(cmBenchmark);
        Benchmark mdbBenchmark = new MapDBBenchmark(MAP_DB);
        mdbBenchmark.run();
        System.out.println(mdbBenchmark);
        Benchmark smdbBenchmark = new SerializedMapDBBenchmark(SERIALIZED_MAP_DB);
        smdbBenchmark.run();
        System.out.println(smdbBenchmark);
    }
}
