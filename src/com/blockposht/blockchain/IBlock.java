package com.blockposht.blockchain;

public interface IBlock {
    BlockData getData();

    default boolean isValid() {
        return getData().isValid();
    }

    default int getReward() {
        return getData().getFee() - getData().getGas();
    }
}
