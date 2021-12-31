package com.blockposht.evolutionary;

public abstract class Player<E extends IGame> {
    protected Strategy<E> strategy;
    protected final int id;
    
    protected Player(int id, Strategy<E> strategy) {
        this.id = id;
        this.strategy = strategy;
    }

    /*
        todo: each player actually has a differnet reward evaluation
    */
    protected abstract void getReward(int reward);
    
    public void play(E game) {
        var act = decide(game);
        game.play(act, id);
    }

    protected Action decide(E game) {
        return strategy.decide(game, game.getActions());
    }
}
