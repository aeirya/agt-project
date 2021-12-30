package com.blockposht.evolutionary.blockchaingame;

import com.blockposht.evolutionary.Strategy;

import javax.swing.text.html.HTMLDocument.HTMLReader.BlockAction;

import com.blockposht.blockchain.forkable.IBlock;
import com.blockposht.evolutionary.Action;

public class HonestStrategy extends Strategy<BlockchainGame> {
    
    // todo: override decide instead of looping
    @Override
    public int evaluate(BlockchainGame env, Action act) {
        BGAction action = (BGAction) act;
        if (action.getType() == BGActionType.ATTACK) 
            return -1;
        
        if (action.getType() == BGActionType.MINE) {
            return evaluate(env, (BGActionMine) action);
        }

        return 0;
    }

    private int evaluate(BlockchainGame env, BGActionMine act) {
        if (!act.getParent().isValid()) return 0;
        if (!act.getBlock().isValid()) return 0;

        // todo: also check other forks
        IBlock chosenBlock = env.getMainChainTip();
        while (!chosenBlock.isValid()) {
            chosenBlock = env.getPredecessor(chosenBlock);
        }
        if (chosenBlock.equals(act.getParent())) return 1;
        return 0;
    }
    
}
