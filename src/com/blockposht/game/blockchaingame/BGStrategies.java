package com.blockposht.game.blockchaingame;

import java.util.List;

import com.blockposht.game.Strategy;

public class BGStrategies {

    private BGStrategies() {}
    
    public static List<Strategy<BlockchainGame>> getAll() {
        return List.of(honest, noobMalicious);
    }

    public static final ProMaliciousStrategy proMalicious(int player) {
        return new ProMaliciousStrategy(player);
    }

    public static final HonestStrategy honest = new HonestStrategy();
    public static final NoobMaliciousStrategy noobMalicious = new NoobMaliciousStrategy();
}
