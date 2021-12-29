package com.blockposht.blockchain.forkable;

import com.blockposht.blockchain.BlockData;
import com.blockposht.blockchain.UserBlock;

public class ChainBlock {
    int height;
    long hash;
    long prevHash;  // hash of previous block
    BlockData data;

    public ChainBlock(int height, long prevHash, BlockData data) {
        this.height = height;
        this.data = data;
        this.prevHash = prevHash;
        this.hash = computeHash(height, prevHash);
    }

    public static ChainBlock of(UserBlock block, ChainBlock parent, int height) {
        return new ChainBlock(height, parent.hash, block.data);
    }
    
    public static ChainBlock genesis() {
        return new ChainBlock(0, 0, BlockData.validData);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof ChainBlock)
            return hash == ((ChainBlock)other).hash;
        // if (other instanceof UserBlock)
        //     return hash == ((UserBlock)other).computeHash();

        return false;
    }

    private static long computeHash(int height, long prevHash) {
        return prevHash * 10 + height;
    }

    @Override
    public String toString() {
        return String.valueOf(hash);
    }
    
}
