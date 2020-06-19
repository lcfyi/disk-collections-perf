package perftest;

import java.io.File;
import java.io.IOException;
import java.util.*;

import perftest.benchmarks.Benchmark;
import perftest.benchmarks.create.*;
import perftest.benchmarks.randomread.*;
import perftest.benchmarks.seqread.ChronicleMapSeqRead;
import perftest.benchmarks.seqread.LevelDBSeqRead;
import perftest.benchmarks.seqread.MapDBSeqRead;
import perftest.benchmarks.seqread.MapSeqRead;
import perftest.benchmarks.seqread.RocksDBSeqRead;
import perftest.benchmarks.seqread.SerializedMapDBSeqRead;


public final class Main {
    private static String TMP_DIR = "tmp";
    private static String CHRONICLE_DB = TMP_DIR + "/chronicle.dat";
    private static String MAP_DB = TMP_DIR + "/mapdb.dat";
    private static String SERIALIZED_MAP_DB = TMP_DIR + "/smapdb.dat";
    private static String LEVEL_DB = TMP_DIR + "/leveldb.dat";
    private static String ROCKS_DB = TMP_DIR + "/rocksdb.dat";
    private static String FILE_NAME = TMP_DIR + "/file.dat";

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

    public static void getFolderSizeWrapper(String filename) throws IOException {
        System.out.print("Size: ");
        System.out.print(getFolderSize(new File(filename)));
        System.out.println("B");
    }

    public static long getFolderSize(File f) throws IOException {
        if (f.isDirectory()) {
            long size = 0;
            for (File c : f.listFiles()) {
                size += getFolderSize(c);
            }
            return size;
        } else {
            return f.length();
        }
    }

    public static void createTests() throws IOException {
        cleanTemporaryDatabases(new File(TMP_DIR));
        Benchmark mapBenchmark = new MapCreate();
        mapBenchmark.run();
        System.out.println(mapBenchmark);
        Benchmark fileBenchmark = new FileCreate(FILE_NAME);
        fileBenchmark.run();
        System.out.println(fileBenchmark);
        getFolderSizeWrapper(FILE_NAME);
        // Benchmark cmBenchmark = new ChronicleMapCreate(CHRONICLE_DB);
        // cmBenchmark.run();
        // System.out.println(cmBenchmark);
        // getFolderSizeWrapper(CHRONICLE_DB);
        // Benchmark mdbBenchmark = new MapDBCreate(MAP_DB);
        // mdbBenchmark.run();
        // System.out.println(mdbBenchmark);
        // getFolderSizeWrapper(MAP_DB);
        // Benchmark smdbBenchmark = new SerializedMapDBCreate(SERIALIZED_MAP_DB);
        // smdbBenchmark.run();
        // System.out.println(smdbBenchmark);
        // getFolderSizeWrapper(SERIALIZED_MAP_DB);
        // Benchmark ldbBenchmark = new LevelDBCreate(LEVEL_DB);
        // ldbBenchmark.run();
        // System.out.println(ldbBenchmark);
        // getFolderSizeWrapper(LEVEL_DB);
        // Benchmark rdbBenchmark = new RocksDBCreate(ROCKS_DB);
        // rdbBenchmark.run();
        // System.out.println(rdbBenchmark);
        // getFolderSizeWrapper(ROCKS_DB);
    }

    public static void sequentialReadTests() throws IOException {
        cleanTemporaryDatabases(new File(TMP_DIR));
        Benchmark mapBenchmark = new MapSeqRead();
        mapBenchmark.run();
        System.out.println(mapBenchmark);
        Benchmark cmBenchmark = new ChronicleMapSeqRead(CHRONICLE_DB);
        cmBenchmark.run();
        System.out.println(cmBenchmark);
        Benchmark mdbBenchmark = new MapDBSeqRead(MAP_DB);
        mdbBenchmark.run();
        System.out.println(mdbBenchmark);
        Benchmark smdbBenchmark = new SerializedMapDBSeqRead(SERIALIZED_MAP_DB);
        smdbBenchmark.run();
        System.out.println(smdbBenchmark);
        Benchmark ldbBenchmark = new LevelDBSeqRead(LEVEL_DB);
        ldbBenchmark.run();
        System.out.println(ldbBenchmark);
        Benchmark rdbBenchmark = new RocksDBSeqRead(ROCKS_DB);
        rdbBenchmark.run();
        System.out.println(rdbBenchmark);
    }

    public static void randomReadTests() throws IOException {
        cleanTemporaryDatabases(new File(TMP_DIR));
        Benchmark mapBenchmark = new MapRandomRead();
        mapBenchmark.run();
        System.out.println(mapBenchmark);
        Benchmark cmBenchmark = new ChronicleMapRandomRead(CHRONICLE_DB);
        cmBenchmark.run();
        System.out.println(cmBenchmark);
        Benchmark mdbBenchmark = new MapDBRandomRead(MAP_DB);
        mdbBenchmark.run();
        System.out.println(mdbBenchmark);
        Benchmark smdbBenchmark = new SerializedMapDBRandomRead(SERIALIZED_MAP_DB);
        smdbBenchmark.run();
        System.out.println(smdbBenchmark);
        Benchmark ldbBenchmark = new LevelDBRandomRead(LEVEL_DB);
        ldbBenchmark.run();
        System.out.println(ldbBenchmark);
        Benchmark rdbBenchmark = new RocksDBRandomRead(ROCKS_DB);
        rdbBenchmark.run();
        System.out.println(rdbBenchmark);
    }

    public static void main(String[] args) throws IOException {
        List<Long> sets = Arrays.asList(1000L, 10000L, 500000L, 1000000L, 2000000L, 5000000L, 10000000L);
        for (Long i : sets) {
            Benchmark.RUN_ITERATIONS = i;
            System.out.println("------------------------------------------------------------------------------------------");
            System.out.printf("                                     %d%n                                             \n", i);
            System.out.println("------------------------------------------------------------------------------------------");
            System.out.println("----------------------------------------  create  ----------------------------------------");
            createTests();
            // System.out.println("---------------------------------------- seq read ----------------------------------------");
            // sequentialReadTests();
        }
    }
}
