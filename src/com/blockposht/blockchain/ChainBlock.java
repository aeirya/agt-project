package com.blockposht.blockchain;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import com.blockposht.utils.serialize.ISerializable;
import com.blockposht.utils.serialize.ISerializer;

public class ChainBlock implements IBlock, ISerializable {
    int height;
    String hash;
    String prevHash;  // hash of previous block
    BlockData data;
    Date proposeDate;
    int userID;

    public ChainBlock(int userID, int height, String prevHash, BlockData data, Date proposeDate) {
        this.height = height;
        this.data = data;
        this.prevHash = prevHash;
        this.proposeDate = proposeDate;
        this.userID = userID;
        this.hash = computeHash(height, prevHash, proposeDate);
    }

    public static ChainBlock of(UserBlock block, ChainBlock parent, int height) {
        return new ChainBlock(block.userID, height, parent.hash, block.data, block.proposeDate);
    }
    
    public static ChainBlock genesis() {
        return new ChainBlock(-1, 0, "0", BlockData.validData, 
            Calendar.getInstance().getTime()
        );
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof ChainBlock)
            return hash == ((ChainBlock)other).hash;
        return false;
    }

    @Override
    public int hashCode() {
        return hash.hashCode();
    }

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

    @Override
    public void serialize(ISerializer ser) throws IOException {
        ser.write(this, ChainBlock.class);
    }
    
}
