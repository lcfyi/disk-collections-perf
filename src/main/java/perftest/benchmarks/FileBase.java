package perftest.benchmarks;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public abstract class FileBase extends Benchmark {

    protected String filename;
    protected BufferedWriter writer;

    public FileBase(String filename) {
        this.filename = filename;
        try {
            writer = new BufferedWriter(new FileWriter(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}