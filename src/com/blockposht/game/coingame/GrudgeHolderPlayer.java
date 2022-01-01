package com.blockposht.game.coingame;

import com.blockposht.game.Action;
import com.blockposht.game.Strategy;

public class GrudgeHolderPlayer extends Strategy<CoinGame> {

    private int player;

    public GrudgeHolderPlayer(int player) {
        this.player = player;
    }

    @Override
    public int evaluate(CoinGame env, Action act) {
        return evaluate(env, (CoinGameAction)act);
    }

    private int evaluate(CoinGame env, CoinGameAction act) {
        if (env.enemyHistory(player).stream().anyMatch(a -> !((CoinGameAction)a).isHonest())) {
            if (!act.isHonest()) return 1;
        } else {
            if (act.isHonest()) return 1;
        }
        return 0;
    }

    
}
