package com.blockposht.evolutionary.blockchaingame;

import java.io.IOException;

import com.blockposht.evolutionary.Player;
import com.blockposht.evolutionary.Strategy;
import com.blockposht.utils.serialize.ISerializable;
import com.blockposht.utils.serialize.ISerializer;

public class BGPlayer extends Player<BlockchainGame> implements ISerializable {

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

    @Override
    public void serialize(ISerializer ser) throws IOException {
        ser.write(this, BGPlayer.class);
    }
    
}
