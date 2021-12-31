package com.blockposht.blockchain;

public interface IBlockchain {
    ChainBlock add(ChainBlock parent, UserBlock block);
}
