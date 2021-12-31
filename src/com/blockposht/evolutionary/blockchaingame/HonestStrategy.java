package com.blockposht.evolutionary.blockchaingame;

import com.blockposht.evolutionary.Strategy;

import javax.swing.text.html.HTMLDocument.HTMLReader.BlockAction;

import com.blockposht.blockchain.forkable.ChainBlock;
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
        ChainBlock longestChainTip = (ChainBlock) env.getMainChainTip();
        // while (!chosenBlockOfLongestChain.isValid()) {
        //     chosenBlockOfLongestChain = env.getPredecessor(chosenBlockOfLongestChain);
        // }


        // var longestChains = env.getNextLongestChain(env.getLongestChain());
        // while (true) {
        //     longestChains.stream().filter(c -> c.getTip().isValid())
        // }

        for (int h = longestChainTip.getHeight(); h>=0 ;--h) {
            var blocks = env.getBlocks(h);
            if (blocks.stream().anyMatch(IBlock::isValid)) {
                if (blocks.contains(act.getParent())) return 1;
                else return 0;
            }
        }

        return 0;
    }
    
}
