package com.blockposht.simulation;

import java.util.List;
import java.util.Random;

import com.blockposht.game.Player;
import com.blockposht.game.blockchaingame.BlockchainGame;
import com.blockposht.utils.serialize.ISerializable;

public class BlockchainSimulation extends GameSimulation<BlockchainGame> {

    private final Random rand;
    
    public BlockchainSimulation(IPopulationGenerator<Player<BlockchainGame>> generator) {
        this(generator.generatePopulation());
    }

    public BlockchainSimulation(List<Player<BlockchainGame>> players) {
        super(
            new BlockchainGame(players),
            players
        );

        rand = new Random();
    }

    /**
     * proceed one round of simulation
     */
    @Override
    public void go() {
        int chosen = rand.nextInt(players.size());
        players.get(chosen).play(game);
    }

    @Override
    public ISerializable getResult() {
        return game;
    }
    
}
