package com.blockposht.evolutionary.coingame;

import com.blockposht.evolutionary.Strategy;

public class CoinGamePlayer {
    private Strategy<CoinGame> strategy;
    private final int id;
    private int money;

    public CoinGamePlayer(int id, Strategy<CoinGame> strategy) {
        this.id = id;
        this.strategy = strategy;
        money = 0;
    }

    public void getReward(int x) {
        money += x;
    }

    public void play(CoinGame game) {
        var act = strategy.decide(game, game.getActions(id));
        game.play(act, id);
    }

    public int getMoney() {
        return money;
    }
    
}
