package com.blockposht;

import com.blockposht.simulation.BGPopulationGenerators;
import com.blockposht.simulation.BlockchainSimulation;
import com.blockposht.utils.serialize.ChainSerializer;

public class App {
    public static void main(String[] args) {
        var simulation = new BlockchainSimulation(
            BGPopulationGenerators.honestAndRational(3, 7)
        );

        simulation.go(30);

        save(simulation, "results/game.json");
    }
    
    static void save(BlockchainSimulation sim, String path) {
        try(ChainSerializer ser = new ChainSerializer(path)) {
            ser.serialize(sim.getResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
