
package com.blockposht.evolutionary.blockchaingame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.blockposht.blockchain.ChainBlock;
import com.blockposht.blockchain.ForkableChain;
import com.blockposht.blockchain.ForkfulBlockchain;
import com.blockposht.blockchain.IBlock;
import com.blockposht.evolutionary.Action;
import com.blockposht.evolutionary.IGame;
import com.blockposht.utils.serialize.ISerializable;
import com.blockposht.utils.serialize.ISerializer;

public class BlockchainGame implements IGame, ISerializable {
    private final ForkfulBlockchain chain;
    private final CyclicList<BGActionMine> recentBlocks;
    private static final int recentBlocksMaxSize = 10;

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
    
    public List<Action> getActions(int player) {
        return recentBlocks.getItems().stream().flatMap(
            (BGActionMine act) -> {
                // todo: convert user block to chain blocks
            IBlock blk = act.getChainblock();
                return Stream.of(
                    new BGActionMine(blk, BGGameParameters.genValidBlock(player)), new BGActionMine(blk, BGGameParameters.genInvalidBlock(player))
                );
            }
        ).collect(Collectors.toList());
        // todo: add attack action
    }

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
        return (ch.contains(block) && ch.size() - block.getHeight() > 5);
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

    public List<ForkableChain> getChains() {
        return chain.getChains();
    }

    @Override
    public void serialize(ISerializer ser) throws IOException {
        List<ISerializable> l = new ArrayList<>();
        l.add(chain);
        l.add(s -> players.forEach(p -> {
            try {
                p.serialize(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
        ser.serialize(l);
    }
    
}
