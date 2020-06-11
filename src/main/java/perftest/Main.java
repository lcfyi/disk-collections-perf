package perftest;

import perftest.benchmarks.*;

public final class Main {
    public static void main(String[] args) {
        Benchmark mapBenchmark = new MapBenchmark();
        mapBenchmark.run();
        System.out.println(mapBenchmark);
        Benchmark cmBenchmark = new ChronicleMapBenchmark("chronicle.dat");
        cmBenchmark.run();
        System.out.println(cmBenchmark);
        Benchmark mdbBenchmark = new MapDBBenchmark("mapdb.dat");
        mdbBenchmark.run();
        System.out.println(mdbBenchmark);
        Benchmark smdbBenchmark = new MapDBBenchmark("smapdb.dat");
        smdbBenchmark.run();
        System.out.println(smdbBenchmark);
    }
}
