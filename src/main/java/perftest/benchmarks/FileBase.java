package perftest.benchmarks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public abstract class FileBase extends Benchmark {

    protected String filename;
    protected BufferedWriter writer;
    protected OutputStream outputStream;

    public FileBase(String filename) {
        this.filename = filename;
        try {
            outputStream = new FileOutputStream(new File(filename));
            writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}