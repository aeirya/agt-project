
package com.blockposht.evolutionary.blockchaingame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.blockposht.blockchain.Block;
import com.blockposht.blockchain.BlockData;
import com.blockposht.blockchain.SmartChain;
import com.blockposht.blockchain.forkable.ChainBlock;
import com.blockposht.blockchain.forkable.ForkableChain;
import com.blockposht.blockchain.forkable.ForkfulBlockchain;
import com.blockposht.blockchain.forkable.IBlock;
import com.blockposht.blockchain.forkable.UserBlock;
import com.blockposht.evolutionary.Action;
import com.blockposht.evolutionary.IGame;
import com.blockposht.evolutionary.Player;
import com.blockposht.evolutionary.Strategy;

public class BlockchainGame implements IGame {
    private final ForkfulBlockchain chain;
    private final CyclicList<BGActionMine> recentBlocks;
    private final int recentBlocksMaxSize = 6;
    private final List<BGPlayer> players;
    private int round = 0;

    public BlockchainGame(List<BGPlayer> players) {
        chain = new ForkfulBlockchain();
        this.players = players;
        recentBlocks = new CyclicList<>(recentBlocksMaxSize);
        mineGenesis();
    }
    
    private void mineGenesis() {
        var act = new BGActionMine(null, chain.getGenesis());
        act.setChainBlock(chain.getGenesis());
        recentBlocks.add(act);
    }
    
    public List<Action> getActions() {
        return recentBlocks.getItems().stream().flatMap(
            (BGActionMine act) -> {
                // todo: convert user block to chain blocks
            IBlock blk = act.getChainblock();
                return Stream.of(
                    new BGActionMine(blk, BGGameParameters.genValidBlock()), new BGActionMine(blk, BGGameParameters.genInvalidBlock())
                );
            }
        ).collect(Collectors.toList());
        // todo: add attack action
    }


    // @Override
    // public List<Action> getActions() {
    //     var tips = chain.getChainTips();
    //     List<ChainBlock> prevs = new ArrayList<>();
    //     for (ChainBlock blk : tips) {
    //         var ch = chain.findOne(blk);
    //         var prev = ch.getPredecessor(blk);
    //         prevs.add(prev);
    //     }
        
    //     tips.stream().map(blk -> chain.findOne(blk))
    //     .map()
    // }

    @Override
    public void play(Action action, int player) {
        play((BGAction) action, player);
    }

    private void play(BGAction action, int player) {
        if (action.getType() == BGActionType.MINE) {
            mineBlock((BGActionMine) action, player);
        }
        ++round;
    }

    private void mineBlock(BGActionMine action, int player) {
        var cb = action.addToChain(chain);
        action.setChainBlock(cb);

        recentBlocks.add(action);
        // todo: change reward system
        players.get(player).getReward(
            action.getBlock().getReward()
        );

    }

    public IBlock getMainChainTip() {
        return chain.getLongestChain().getTip();
    }

    public IBlock getPredecessor(IBlock blk) {
        return chain.getPredecessor(blk);
    }

    public boolean isCannon(ChainBlock block) {
        if (block == null) return false;

        var ch = chain.getLongestChain();
        return (ch.contains(block) && ch.size() - block.getHeight() > 0);
    }

    public ForkableChain getLongestChain() {
        return chain.getLongestChain();
    }

    public List<ForkableChain> getNextLongestChain(ForkableChain c) {
        return chain.getNextLongestChain(c);
    }

    public List<ChainBlock> getBlocks(int height) {
        return chain.get(height);
    }
    
}
