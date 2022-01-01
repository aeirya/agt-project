package com.blockposht.evolutionary.blockchaingame;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.blockposht.blockchain.ChainBlock;
import com.blockposht.evolutionary.Strategy;
import com.google.gson.GsonBuilder;

public class BGManager {
    public static void main(String[] args) {
        Random rand = new Random();
        List<BGPlayer> players = genPopulation(100, 40);
        BlockchainGame game = new BlockchainGame(players);
        // var strategies = BGStrategies.getAll();
        
        int rounds = 10000;
        for (int i=0; i<rounds; ++i) {
            int chosen = rand.nextInt(players.size());
            players.get(chosen).play(game);
        }


        System.out.println(((ChainBlock)game.getMainChainTip()).getHeight());


        var json = new GsonBuilder().setPrettyPrinting().create().toJson(game);
        try(var fw = new FileWriter(new File("game.json"))) {
            fw.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();

        // todo: extract json results
    }

    static List<BGPlayer> genPopulation(int nHonest, int nRational) {
        List<BGPlayer> players = new ArrayList<>();
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
}
