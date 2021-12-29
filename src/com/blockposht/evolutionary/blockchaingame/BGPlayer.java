package com.blockposht.evolutionary.blockchaingame;

import com.blockposht.evolutionary.Player;
import com.blockposht.evolutionary.Strategy;

public class BGPlayer extends Player<BlockchainGame> {

    protected BGPlayer(int id, Strategy<BlockchainGame> strategy) {
        super(id, strategy);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void getReward(int reward) {
        // do nothing
    }
    
}
