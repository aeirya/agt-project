package com.blockposht.evolutionary.blockchaingame;

import com.blockposht.evolutionary.Player;
import com.blockposht.evolutionary.Strategy;

public class BGPlayer extends Player<BlockchainGame> {

    private int money;
    private int lastMatchReward;
    
    protected BGPlayer(int id, Strategy<BlockchainGame> strategy) {
        super(id, strategy);
        money = 0;
    }

    @Override
    public void getReward(int reward) {
        // if (reward < lastMatchReward) then switch back
        money += reward;


        // todo: reflect uppon
    }
    
}
