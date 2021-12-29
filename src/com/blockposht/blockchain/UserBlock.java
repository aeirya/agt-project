package com.blockposht.blockchain;

import java.util.Calendar;
import java.util.Date;

public class UserBlock {
    public final Date proposeDate;
    public final BlockData data;

    public UserBlock(Date proposeDate, BlockData data) {
        this.proposeDate = proposeDate;
        this.data = data;
    }

    public UserBlock(BlockData data) {
        this(Calendar.getInstance().getTime(), data);
    }

    long computeHash(int height) {
        return proposeDate.getTime()*10 + height;
    }

    public static class Dummy extends UserBlock {
        public Dummy() {
            super(Calendar.getInstance().getTime(), BlockData.validData);
        }
    }


}
