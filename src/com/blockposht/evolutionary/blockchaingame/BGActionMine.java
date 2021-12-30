package com.blockposht.evolutionary.blockchaingame;

import com.blockposht.blockchain.forkable.ChainBlock;
import com.blockposht.blockchain.forkable.IBlock;
import com.blockposht.blockchain.forkable.IBlockchain;
import com.blockposht.blockchain.forkable.UserBlock;

public class BGActionMine extends BGAction {
    private final IBlock parent;
    private final IBlock block;
    private ChainBlock chainBlock;

    public BGActionMine(IBlock parent, IBlock block) {
        super(BGActionType.MINE);
        this.parent = parent;
        this.block = block;
    }

    public IBlock getParent() {
        return parent;
    }

    public IBlock getBlock() {
        return block;
    }

    public ChainBlock addToChain(IBlockchain chain) {
        return chain.add((ChainBlock)parent, (UserBlock)block);
    }

    public void setChainBlock(ChainBlock block) {
        this.chainBlock = block;
    }

    public ChainBlock getChainblock() {
        return this.chainBlock;
    }
}
