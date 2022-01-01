package com.blockposht.game;

import java.util.List;

public interface IGame {
    List<Action> getActions(int player);
    void play(Action action, int player);
}
