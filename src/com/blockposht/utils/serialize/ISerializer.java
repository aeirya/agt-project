package com.blockposht.utils.serialize;

import java.io.IOException;
import java.util.List;

public interface ISerializer extends AutoCloseable {
    <T extends ISerializable> void serialize(List<T> items) throws IOException;
    <T extends ISerializable> void serialize(T item) throws IOException;
    <T> void serialize(T item);
    <T> void write(T item, Class<T> clazz);
    void close() throws Exception;
}
