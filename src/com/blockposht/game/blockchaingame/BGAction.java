package com.blockposht.game.blockchaingame;

import com.blockposht.game.Action;

public class BGAction extends Action {
    private final BGActionType type;

    public BGAction(BGActionType type) {
        this.type = type;
    }

    public BGActionType getType() {
        return type;
    }
}
