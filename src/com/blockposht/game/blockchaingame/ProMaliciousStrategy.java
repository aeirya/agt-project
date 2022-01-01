package com.blockposht.game.blockchaingame;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.blockposht.blockchain.ChainBlock;
import com.blockposht.blockchain.ForkableChain;
import com.blockposht.game.Action;
import com.blockposht.game.Strategy;

public class ProMaliciousStrategy extends Strategy<BlockchainGame> {

    private final int user;
    private final float p = 0.2f;

    public ProMaliciousStrategy(int user) {
        this.user = user;
    }

    @Override
    public int evaluate(BlockchainGame env, Action act) {
        return evaluate(env, (BGAction) act);
    }

    private int evaluate(BlockchainGame env, BGAction act) {
        if (act.getType().equals(BGActionType.MINE))
            return evaluate(env, (BGActionMine) act);
        else return 0;
    }

    private int evaluate(BlockchainGame env, BGActionMine act) {
        
        var longestChain = env.getLongestChain();
        int H = longestChain.size();
        if (!isAcceptableHeight(act.getParent().getHeight(), H)) return 0;
        
        var chains = env.getChains().stream().filter(c -> isAcceptableHeight(c.size(), H)).collect(Collectors.toList()); 
        
        var firstForkedChain = chains.stream().reduce((x,y)->x.findCommonForkedChain(y)).orElseThrow();
        // todo: change i formula
        int i = firstForkedChain.getForkHeight();

        var blk = act.getParent();
        int M = chains.stream()
            .filter(c -> c.contains(blk))
            .findAny()
            .map(c -> evalChainBlock(c, blk, i))
            .orElse(0);

        
        // todo: check this
        if (0.7 * M > BGGameParameters.INVALID_FEE - BGGameParameters.VALID_FEE) {
            if (!act.getBlock().isValid()) return act.getBlock().getReward(); // or 0, idk
            return M + act.getBlock().getReward();
        }
        
        return M + act.getBlock().getReward();
        // else return 0.2*

        // var e = chains.stream()
        //     .map(c -> evalChainBlocks(c, i, H))
        //     .flatMap(l -> l.stream())
        //     .collect(Collectors.maxBy((e1,e2) -> e1.getValue()-e2.getValue()))
        //     .get();
    }

    private List<Entry<ChainBlock, Integer>> evalChainBlocks(ForkableChain chain, int minHeight, int longestChainSize) {
        List<Entry<ChainBlock, Integer>> m = new ArrayList<>();
        ChainBlock blk;
        int height = minHeight;
        int val = 0;
        do {
            blk = chain.get(height);
            if (blk.getUserID() == user) val += blk.getReward();
            if (isAcceptableHeight(height, longestChainSize)) {
                m.add(Map.entry(blk, val));
            }
            ++height;
        } while (blk.getHeight() < chain.size());
        
        return m;
    }

    private int evalChainBlock(ForkableChain chain, ChainBlock block, int from) {
        return IntStream.rangeClosed(from, block.getHeight()).map(i -> {
            var b = chain.get(i);
            if (b.getUserID() == user) 
                return b.getReward();
            return 0;
        }).sum();
    }

    private boolean isAcceptableHeight(int height, int longestChainSize) {
        return height + 4 > longestChainSize;
    }
}
