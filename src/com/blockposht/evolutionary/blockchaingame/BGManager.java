package com.blockposht.evolutionary.blockchaingame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BGManager {
    public static void main(String[] args) {
        Random rand = new Random();
        List<BGPlayer> players = new ArrayList<>();
        BlockchainGame game = new BlockchainGame(players);
        int n = 5;
        // var strategies = BGStrategies.getAll();
        for (int i=0; i<n; ++i) {
            players.add(
                new BGPlayer(i, BGStrategies.honest)
            );
        }
        int rounds = 30;
        for (int i=0; i<rounds; ++i) {
            int chosen = rand.nextInt(players.size());
            players.get(chosen).play(game);
        }



        System.out.println();

        // todo: extract json results
    }
}
