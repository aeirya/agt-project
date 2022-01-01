package com.blockposht.utils.serialize;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;

/*
    converts to json and writes to output stream
*/
public class ChainSerializer implements ISerializer {
    private final Gson gson;
    private final JsonWriter writer;

    public ChainSerializer(String path) throws FileNotFoundException {
        this(
            new GsonBuilder().setPrettyPrinting().create(), 
            new FileOutputStream(new File(path))
        );
    }

    public ChainSerializer(Gson gson, OutputStream out) {
        this.gson = gson;
        this.writer = new JsonWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));
        writer.setIndent("    ");
    }

    public <T extends ISerializable> void serializeObject(T item) throws IOException {
        writer.beginObject();
        item.serialize(this);
        writer.endObject();
    }

    @Override
    public <T extends ISerializable> void serialize(List<T> items) throws IOException {
        writer.beginArray();
        for (T it : items) {
            serialize(it);
        }
        writer.endArray();
    }
    
    @Override
    public <T extends ISerializable> void serialize(T item) throws IOException {
        item.serialize(this);
    }

    @Override
    public <T> void write(T item, Class<T> clazz) {
        gson.toJson(item, clazz, writer);
    }

    public void close() throws IOException {
        writer.close();
    }

    @Override
    public <T> void serialize(T item) {
        gson.toJson(item, item.getClass() , writer);    
    }

    public void beginObject() throws IOException {
        // writer.beginObject();
        writer.beginArray();
    }

    public void endObject() throws IOException {
        // writer.endObject();
        writer.endArray();
    }

    public void name(String name) throws IOException {
        writer.name(name);
    }

    public static void main(String[] args) throws IOException {
        var ser = new ChainSerializer("test.json");
        ser.writer.beginObject();
        ser.writer.name("field");
        ser.writer.value("hello");
        ser.writer.endObject();
        ser.writer.flush();
    }
}
