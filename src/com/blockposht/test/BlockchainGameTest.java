package com.blockposht.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.blockposht.game.blockchaingame.BlockchainGame;
import com.blockposht.simulation.BGPopulationGenerators;
import com.blockposht.simulation.BlockchainSimulation;

import org.junit.Test;

public class BlockchainGameTest {
    @Test
    public void checkChainLength() {
        var simulation = new BlockchainSimulation(
            BGPopulationGenerators.honestAndRational(3, 7)
        );

        simulation.go(30);

        var game = simulation.getGame();
        var state = ((BlockchainGame) game).getState();
        assertTrue(state.chain.getLongestChain().size() <= 30);
    }
}


