package com.blockposht.evolutionary.blockchaingame;

import com.blockposht.blockchain.Block;
import com.blockposht.evolutionary.Action;

public class BGAction extends Action {
    private final Block block;

    public BGAction(Block block) {
        this.block = block;
    }

    Block act() {
        return block; 
    }
}
