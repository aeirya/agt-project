package com.blockposht.simulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.blockposht.game.Player;
import com.blockposht.game.Strategy;
import com.blockposht.game.blockchaingame.BGPlayer;
import com.blockposht.game.blockchaingame.BGRationalPlayer;
import com.blockposht.game.blockchaingame.BGStrategies;
import com.blockposht.game.blockchaingame.BlockchainGame;

public class BGPopulationGenerators {
    private BGPopulationGenerators() {}

    public static IPopulationGenerator<Player<BlockchainGame>> honestAndRational(
        int nHonest, int nRational
    ) {
        return () ->  genPopulation(nHonest, nRational);
    }

    static List<Player<BlockchainGame>> genPopulation(int nHonest, int nRational) {
        List<Player<BlockchainGame>> players = new ArrayList<>();
        int id = 0;
        for (int i=0; i<nHonest; ++i)
            players.add(
                new BGPlayer(id++, BGStrategies.honest)
            );
        
        List<Strategy<BlockchainGame>> S = new ArrayList<>();
        S.add(BGStrategies.honest);
        S.add(BGStrategies.noobMalicious);

        for (int i=0; i<nRational; ++i)
            players.add(new BGRationalPlayer(id++, S));

        return players;
    }

    static List<BGPlayer> genProPopulation(int idZero, int n) {
        return IntStream.range(idZero, idZero+n)
            .mapToObj(id -> new BGRationalPlayer(id, Arrays.asList(BGStrategies.proMalicious(id))))
            // .mapToObj(id -> new BGRationalPlayer(id, Arrays.asList(BGStrategies.honest, BGStrategies.noobMalicious, BGStrategies.proMalicious(id))))
            .collect(Collectors.toList());
    }
}
