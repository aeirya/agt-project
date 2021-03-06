package com.blockposht.game.coingame;

import com.blockposht.game.Action;
import com.blockposht.game.Strategy;

public class CopycatPlayer extends Strategy<CoinGame> {
    private final int id;

    public CopycatPlayer(int playerID) {
        this.id = playerID;
    }

    @Override
    public int evaluate(CoinGame env, Action act) {
        var lastAction = env.enemyLastAction(id);
        if (lastAction == null) {
            if (act instanceof CoinGameAction.Honest) return 1;
            return 0;
        }

        if (lastAction instanceof CoinGameAction.Honest && act instanceof CoinGameAction.Honest) return 1;
        if (lastAction instanceof CoinGameAction.Steal && act instanceof CoinGameAction.Steal) return 1;
        return 0;
    }
}
