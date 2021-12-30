package com.blockposht.evolutionary.blockchaingame;

import com.blockposht.blockchain.BlockData;
import com.blockposht.blockchain.forkable.IBlock;
import com.blockposht.blockchain.forkable.UserBlock;

public class BGGameParameters {
    public static final int invalidFee = 100;
    public static final int validFee = 20;
    public static final int maximumFee = 200;
    public static final int gas = 10;

    public static IBlock genValidBlock() {
        return new UserBlock(new BlockData(true, validFee, gas));
    }

    public static IBlock genInvalidBlock() {
        return new UserBlock(new BlockData(false, invalidFee, gas));
    }

    public static IBlock genValidProfitableBlock() {
        return new UserBlock(new BlockData(true, maximumFee, gas));
    }
  
}
