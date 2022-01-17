package com.blockposht.simulation;

import java.util.List;

import com.blockposht.game.IGame;
import com.blockposht.game.Player;
import com.blockposht.utils.serialize.ISerializable;

public abstract class GameSimulation<T extends IGame> implements ISimulation {

    protected final List<Player<T>> players;
    protected final T game;

    GameSimulation(T game, List<Player<T>> players) {
        this.game = game;
        this.players = players;
    }

    /* todo: replace this with game final state */
    public IGame getGame() {
        return game;
    }

    @Override
    public ISerializable getResult() {
        return (ISerializable) game;    // warning: game may or may not be serializable!
                                        // definite a serializable game data class
    }
}
