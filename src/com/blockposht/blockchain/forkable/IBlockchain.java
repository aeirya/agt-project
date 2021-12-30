package com.blockposht.blockchain.forkable;

public interface IBlockchain {
    ChainBlock add(ChainBlock parent, UserBlock block);
}
