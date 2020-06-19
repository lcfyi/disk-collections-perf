package perftest.benchmarks.seqread;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import perftest.benchmarks.FileBase;
import perftest.serial.SerializedKey;
import perftest.serial.SerializedValue;

public class FileSeqRead extends FileBase {
    List<SerializedValue> keys;
    int idx;

    InputStream inputStream = null;
    Reader decoder = null;
    BufferedReader reader = null;
    ObjectReader MAPPER;

    public FileSeqRead(String filename) {
        super(filename);
        keys = new ArrayList<>();
        MAPPER = new ObjectMapper().reader().forType(SerializedValue.class);
        idx = 0;
    }

    @Override
    public void init() {
        for (int i = 0; i < RUN_ITERATIONS; i++) {
            SerializedKey key = new SerializedKey(UUID.randomUUID().toString(), UUID.randomUUID().toString());
            SerializedValue value = new SerializedValue(key);
            try {
                writer.write(value.toString());
                writer.newLine();
                keys.add(value);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            writer.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            inputStream = new FileInputStream(new File(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            decoder = new InputStreamReader(inputStream, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        reader = new BufferedReader(decoder);
    }

    @Override
    public void work() {
        try {
            if (!MAPPER.readValue(reader.readLine()).equals(keys.get(idx))) {
                throw new RuntimeException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        idx++;
    }

    @Override
    public void teardown() {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
