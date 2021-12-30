package com.blockposht.evolutionary;

public abstract class Player<E extends IGame> {
    protected Strategy<E> strategy;
    protected final int id;
    
    protected Player(int id, Strategy<E> strategy) {
        this.id = id;
        this.strategy = strategy;
    }

    protected abstract void getReward(int reward);
    
    public void play(E game) {
        var act = strategy.decide(game, game.getActions());
        game.play(act, id);
    }
}
