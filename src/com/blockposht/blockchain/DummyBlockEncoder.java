package com.blockposht.blockchain;

public class DummyBlockEncoder implements IBlockEncoder {
    public String computeHash(Block blk) {
        return String.format("%d%d", blk.height, blk.date.getTime());
    }
}
