package com.blockposht.evolutionary.blockchaingame;

import java.util.List;

import com.blockposht.evolutionary.Player;
import com.blockposht.evolutionary.Strategy;

public class BGRationalPlayer extends Player<BlockchainGame> {
    public BGRationalPlayer(int id, List<Strategy<BlockchainGame>> strategies) {
        super(id, strategies.get(0));
    }

    @Override
    protected void getReward(int reward) {
        // TODO Auto-generated method stub
        
    }
}
