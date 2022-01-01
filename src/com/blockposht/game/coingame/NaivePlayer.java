package com.blockposht.game.coingame;

import com.blockposht.game.Action;
import com.blockposht.game.Strategy;

public class NaivePlayer extends Strategy<CoinGame> {

    @Override
    public int evaluate(CoinGame env, Action act) {
        if (act instanceof CoinGameAction.Honest) 
            return 1;
        else return 0;
    }

    public static final NaivePlayer INSTANCE = new NaivePlayer();
    
}
