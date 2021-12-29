package com.blockposht.evolutionary.blockchaingame;

import com.blockposht.evolutionary.Strategy;
import com.blockposht.blockchain.Block;
import com.blockposht.evolutionary.Action;

public class HonestStrategy extends Strategy<BlockchainGame> {
    
    // todo: override decide instead of looping
    @Override
    public int evaluate(BlockchainGame env, Action act) {
        Block actBlk = ((BGAction)act).act();
        if (!actBlk.isValid()) return 0;

        // todo: also check other forks
        Block chosenBlock = env.getMainChain();
        while (!chosenBlock.isValid()) {
            chosenBlock = env.getPredecessor(chosenBlock);
        }
        if (chosenBlock.isEqual(actBlk)) return 1;
        return 0;
    }
    
}
