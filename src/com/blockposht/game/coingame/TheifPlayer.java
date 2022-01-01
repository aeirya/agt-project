package com.blockposht.game.coingame;

import com.blockposht.game.Action;
import com.blockposht.game.Strategy;

public class TheifPlayer extends Strategy<CoinGame> {
    
    @Override
    public int evaluate(CoinGame env, Action act) {
        if (act instanceof CoinGameAction.Honest) 
            return 0;
        else return 1;
    }

    public static final TheifPlayer INSTANCE = new TheifPlayer();
}
