package com.blockposht.game.coingame;

import com.blockposht.game.Action;

public abstract class CoinGameAction extends Action {
    abstract boolean isHonest();

    public static class Honest extends CoinGameAction {
        boolean isHonest() {
            return true;
        }
    }

    public static class Steal extends CoinGameAction {
        boolean isHonest() {
            return false;
        }
    }

    public static final Honest HONEST = new Honest();
    public static final Steal STEAL = new Steal();
}

