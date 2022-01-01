package com.blockposht.evolutionary.blockchaingame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.blockposht.blockchain.ChainBlock;
import com.blockposht.evolutionary.Strategy;
import com.blockposht.utils.serialize.ChainSerializer;

public class BGManager {
    public static void main(String[] args) {
        Random rand = new Random();
        List<BGPlayer> players = genPopulation(0, 10);
        BlockchainGame game = new BlockchainGame(players);
        // var strategies = BGStrategies.getAll();
        
        int rounds = 500;
        for (int i=0; i<rounds; ++i) {
            int chosen = rand.nextInt(players.size());
            players.get(chosen).play(game);
        }


        System.out.println(((ChainBlock)game.getMainChainTip()).getHeight());
        
        try(ChainSerializer ser = new ChainSerializer("game.json")) {
            ser.serialize(game);
        } catch (Exception e) {
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

    // public void writeJsonStream(OutputStream out, List<Message> messages) throws IOException {
    //     JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
    //     writer.setIndent("  ");
    //     writer.beginArray();
    //     for (Message message : messages) {
    //         gson.toJson(message, Message.class, writer);
    //     }
    //     writer.endArray();
    //     writer.close();
    // }
}
