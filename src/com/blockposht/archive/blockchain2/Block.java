package com.blockposht.archive.blockchain2;

import java.util.Date;

import com.blockposht.blockchain.BlockData;

public class Block {
    protected final int height;         // height of the block in the chain
    protected final Date date;          // seconds since the creation of genesis block
    protected final BlockData data;
    protected String previousBlockHash;
    private final String hash;
    
    public Block(int height, Date date, BlockData data, String previousBlockHash) {
        this.date = date;
        this.data = data;
        this.previousBlockHash = previousBlockHash;
        this.height = height;
        this.hash = new DummyBlockEncoder().computeHash(this);
    }

    public int getHeight() {
        return height;
    }

    public String getHash() {
        return hash;
    }

    public boolean isEqual(Block obj) {
        if (obj == null) return false;
        return hash.equals(obj.hash);
    }

    public boolean isValid() {
        return data.isValid();
    }

    @Override
    public String toString() {
        return getHash();
    }
}
