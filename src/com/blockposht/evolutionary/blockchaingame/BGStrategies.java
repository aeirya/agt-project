package com.blockposht.evolutionary.blockchaingame;

import java.util.List;

import com.blockposht.evolutionary.Strategy;

public class BGStrategies {
    public static List<Strategy<BlockchainGame>> getAll() {
        return List.of(honest);
    }

    public static final HonestStrategy honest = new HonestStrategy();
}
