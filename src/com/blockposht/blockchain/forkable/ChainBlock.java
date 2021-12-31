package com.blockposht.blockchain.forkable;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import com.blockposht.blockchain.BlockData;

public class ChainBlock implements IBlock {
    int height;
    String hash;
    String prevHash;  // hash of previous block
    BlockData data;
    Date proposeDate;

    public ChainBlock(int height, String prevHash, BlockData data, Date proposeDate) {
        this.height = height;
        this.data = data;
        this.prevHash = prevHash;
        this.proposeDate = proposeDate;
        this.hash = computeHash(height, prevHash, proposeDate);
    }

    public static ChainBlock of(UserBlock block, ChainBlock parent, int height) {
        return new ChainBlock(height, parent.hash, block.data, block.proposeDate);
    }
    
    public static ChainBlock genesis() {
        return new ChainBlock(0, "0", BlockData.validData, 
            Calendar.getInstance().getTime()
        );
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof ChainBlock)
            return hash == ((ChainBlock)other).hash;
        return false;
    }

    // @Override
    // public int hashCode() {
    //     return ;
    // }

    private static String computeHash(int height, String prevHash, Date proposeDate) {
        // tood: change this
        return UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return String.valueOf(hash);
    }

    @Override
    public BlockData getData() {
        return data;
    }

    public int getHeight() {
        return height;
    }
    
}
