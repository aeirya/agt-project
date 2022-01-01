package com.blockposht.blockchain;

import java.util.Calendar;
import java.util.Date;

public class UserBlock implements IBlock {
    // todo: add proposer user id
    public final Date proposeDate;
    public final BlockData data;
    public final int userID;

    public UserBlock(int userID, Date proposeDate, BlockData data) {
        this.proposeDate = proposeDate;
        this.data = data;
        this.userID = userID;
    }

    public UserBlock(int userID, BlockData data) {
        this(userID, Calendar.getInstance().getTime(), data);
    }

    public UserBlock(int userID, Boolean isValid, int fee, int gas) {
        this(userID, new BlockData(isValid, fee, gas));
    }

    long computeHash(int height) {
        return proposeDate.getTime()*10 + height;
    }

    public static class Dummy extends UserBlock {
        public Dummy() {
            this(-1);
        }
        
        public Dummy(int id) {
            super(id, Calendar.getInstance().getTime(), BlockData.validData);
        }
    }

    @Override
    public BlockData getData() {
        return data;
    }

}
