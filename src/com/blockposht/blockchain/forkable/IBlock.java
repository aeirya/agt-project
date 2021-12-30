package com.blockposht.blockchain.forkable;

import com.blockposht.blockchain.BlockData;

public interface IBlock {
    BlockData getData();

    default boolean isValid() {
        return getData().isValid();
    }

    default int getReward() {
        return getData().getFee() - getData().getGas();
    }
}
