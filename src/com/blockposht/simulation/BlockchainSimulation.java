package com.blockposht.simulation;

import java.util.List;

import com.blockposht.game.Player;
import com.blockposht.game.blockchaingame.BlockchainGame;
import com.blockposht.utils.Vector;
import com.blockposht.utils.random.RandomUtils;
import com.blockposht.utils.serialize.ISerializable;

public class BlockchainSimulation extends GameSimulation<BlockchainGame> {

    private final RandomUtils rand;
    private Vector hashPowerDist;

    public BlockchainSimulation(IPopulationGenerator<Player<BlockchainGame>> generator) {
        this(generator.generatePopulation());
    }

    public BlockchainSimulation(List<Player<BlockchainGame>> players) {
        super(
            new BlockchainGame(players),
            players
        );

        rand = new RandomUtils();
    }

    /**
     * proceed one round of simulation
     */
    @Override
    public void go() {
        hashPowerDist = rand.randomVector(players.size());
        int chosen = rand.choose(hashPowerDist);
        players.get(chosen).play(game);
    }

    @Override
    public ISerializable getResult() {
        return game;
    }
    
}
