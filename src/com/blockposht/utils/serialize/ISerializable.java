package com.blockposht.utils.serialize;

import java.io.IOException;

public interface ISerializable {
    void serialize(ISerializer ser) throws IOException;
}
