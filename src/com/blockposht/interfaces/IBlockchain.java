package com.blockposht.interfaces;

import com.blockposht.blockchain.Block;

public interface IBlockchain {
    void addBlock(Block parent, Block blk);
    Block getTip();
}
