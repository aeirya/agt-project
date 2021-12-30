package com.blockposht.evolutionary.blockchaingame;

import com.blockposht.evolutionary.Action;

public class BGAction extends Action {
    private final BGActionType type;

    public BGAction(BGActionType type) {
        this.type = type;
    }

    public BGActionType getType() {
        return type;
    }
}
