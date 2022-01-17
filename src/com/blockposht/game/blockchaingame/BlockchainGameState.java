package com.blockposht.game.blockchaingame;

import java.util.List;

import com.blockposht.blockchain.ForkfulBlockchain;
import com.blockposht.game.Player;

public class BlockchainGameState {
    public final ForkfulBlockchain chain;
    public final List<BGActionMine> recentBlocks;
    public final List<Player<BlockchainGame>> players;

    public BlockchainGameState(
        ForkfulBlockchain chain, 
        List<BGActionMine> recentBlocks, 
        List<Player<BlockchainGame>> players
    ) {
        this.chain = chain;
        this.recentBlocks = recentBlocks;
        this.players = players;
    }
    
}
