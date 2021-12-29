package com.blockposht.blockchain;

import java.util.Calendar;

public class DummyBlock extends Block {
    public DummyBlock(int height, String pHash) {
        super(height, Calendar.getInstance().getTime(), BlockData.validData, pHash);
    }
}
