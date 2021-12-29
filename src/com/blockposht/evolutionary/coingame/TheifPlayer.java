package com.blockposht.evolutionary.coingame;

import com.blockposht.evolutionary.Action;
import com.blockposht.evolutionary.Strategy;

public class TheifPlayer extends Strategy<CoinGame> {
    
    @Override
    public int evaluate(CoinGame env, Action act) {
        if (act instanceof CoinGameAction.Honest) 
            return 0;
        else return 1;
    }

    public static final TheifPlayer INSTANCE = new TheifPlayer();
}
