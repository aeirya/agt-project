package com.blockposht.blockchain.forkable;

import java.util.Calendar;
import java.util.Date;

import com.blockposht.blockchain.BlockData;

public class UserBlock implements IBlock {
    // todo: add proposer user id
    public final Date proposeDate;
    public final BlockData data;
    public final int userID;

    public UserBlock(Date proposeDate, BlockData data) {
        this.proposeDate = proposeDate;
        this.data = data;
    }

    public UserBlock(BlockData data) {
        this(Calendar.getInstance().getTime(), data);
    }

    public UserBlock(Boolean isValid, int fee, int gas) {
        this(new BlockData(isValid, fee, gas));
    }

    long computeHash(int height) {
        return proposeDate.getTime()*10 + height;
    }

    public static class Dummy extends UserBlock {
        public Dummy() {
            super(Calendar.getInstance().getTime(), BlockData.validData);
        }
    }

    @Override
    public BlockData getData() {
        return data;
    }

}
