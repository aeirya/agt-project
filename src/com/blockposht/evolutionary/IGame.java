package com.blockposht.evolutionary;

import java.util.List;

public interface IGame {
    List<Action> getActions();
    void play(Action action, int player);
}
