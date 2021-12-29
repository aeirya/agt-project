package com.blockposht.archive.sha;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Sha256 {

    private static final Charset UTF_8 = StandardCharsets.UTF_8;

    private Sha256() {}

    private static String digest(byte[] input) {
        return ShaUtils.bytesToHex(
            ShaUtils.digest(input, "SHA3-256")
        );
    }

    public static String digest(String input) {
        return digest(input.getBytes(UTF_8));
    }

    
}
