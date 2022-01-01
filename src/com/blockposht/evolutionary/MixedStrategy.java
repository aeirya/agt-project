package com.blockposht.evolutionary;

import java.util.List;

public class MixedStrategy<E> extends Strategy<E> {
    private List<Strategy<E>> strategies;
    private List<Float> p;

    public MixedStrategy(List<Strategy<E>> strategies) {
        this.strategies = strategies;
    }

    @Override
    public int evaluate(Object env, Action act) {
        // TODO Auto-generated method stub
        return 0;
    }
}
