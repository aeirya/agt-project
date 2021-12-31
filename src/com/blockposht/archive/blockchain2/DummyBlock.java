package com.blockposht.archive.blockchain2;

import java.util.Calendar;

import com.blockposht.blockchain.BlockData;

public class DummyBlock extends Block {
    public DummyBlock(int height, String pHash) {
        super(height, Calendar.getInstance().getTime(), BlockData.validData, pHash);
    }
}
