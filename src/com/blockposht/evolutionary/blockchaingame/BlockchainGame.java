
package com.blockposht.evolutionary.blockchaingame;

import java.util.List;

import com.blockposht.blockchain.Block;
import com.blockposht.blockchain.SmartChain;
import com.blockposht.evolutionary.Action;
import com.blockposht.evolutionary.IGame;

public class BlockchainGame implements IGame {
    private final SmartChain chain;
    // todo: do the random picking here! not in smart chain
    
    public BlockchainGame() {
        chain = new SmartChain();
    }

    @Override
    public List<Action> getActions() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void play(Action action, int player) {
        // TODO Auto-generated method stub
        
    }

    public Block getMainChain() {
        return chain.getTip();
    }

    public Block getPredecessor(Block blk) {
        return chain.getPredecessor(blk);
    }
    
}
