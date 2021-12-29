package com.blockposht.test;

import java.util.HashMap;
import java.util.Map;

public class MemoryTest {

    public static class Block {
        public int value = 0;
    }

    public static void main(String[] args) {
        Map<Integer, Block> map = new HashMap<>();
        Block b = new Block();

        map.put(1, b);
        b.value = 2;
        System.out.println(map.get(1).value);

    }
}
