package perftest.benchmarks;

import java.util.*;

public abstract class Benchmark {
    public static final long RUN_ITERATIONS = 1_000_000;
    private final List<Long> runtimes = new ArrayList<>();
    private long duration;

    /**
     * Run the benchmark.
     */
    public void run() {
        init();
        long start = System.nanoTime();
        for (long i = 0; i < RUN_ITERATIONS; i++) {
            long inst = System.nanoTime();
            work();
            runtimes.add(System.nanoTime() - inst);
        }
        duration = System.nanoTime() - start;
        teardown();
    }

    public long maxRuntime() {
        long max = Long.MIN_VALUE;
        for (long e : runtimes) {
            if (e > max)
                max = e;
        }
        return max;
    }

    public long minRuntime() {
        long min = Long.MAX_VALUE;
        for (long e : runtimes) {
            if (e < min)
                min = e;
        }
        return min;
    }

    public double avgRuntime() {
        long tot = runtimes.stream().reduce(0L, (subtotal, element) -> subtotal + element);
        return (double) tot / (double) runtimes.size();
    }

    public long benchDuration() {
        return duration;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("----------- ");
        builder.append(this.getClass().toString());
        builder.append(" -----------\n");
        builder.append("Average: ");
        builder.append(avgRuntime());
        builder.append("\n");
        builder.append("Max: ");
        builder.append(maxRuntime());
        builder.append("\n");
        builder.append("Min: ");
        builder.append(minRuntime());
        builder.append("\n");
        builder.append("Total: ");
        builder.append(benchDuration());
        builder.append("\n");
        builder.append("All numbers are in nanoseconds\n");
        return builder.toString();
    }

    /**
     * Any sort of initialization should go here (fixtures, etc).
     */
    public abstract void init();

    /**
     * This function should be the smallest unit of work.
     */
    public abstract void work();

    /**
     * Any teardown should go here.
     */
    public abstract void teardown();
}
