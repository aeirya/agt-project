package com.blockposht.game.blockchaingame;

import com.blockposht.blockchain.BlockData;
import com.blockposht.blockchain.UserBlock;

public class BGGameParameters {
    
    public static final int INVALID_FEE = 100;
    public static final int VALID_FEE = 20;
    public static final int MAX_FEE = 200;
    public static final int GAS = 10;

    private BGGameParameters() {}

    public static UserBlock genValidBlock(int player) {
        return new UserBlock(player, new BlockData(true, VALID_FEE, GAS));
    }

    public static UserBlock genInvalidBlock(int player) {
        return new UserBlock(player, new BlockData(false, INVALID_FEE, GAS));
    }

    public static UserBlock genValidProfitableBlock(int player) {
        return new UserBlock(player, new BlockData(true, MAX_FEE, GAS));
    }
  
}
