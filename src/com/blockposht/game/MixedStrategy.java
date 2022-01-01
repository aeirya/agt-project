package com.blockposht.game;

import java.util.List;
import java.util.Random;

import com.blockposht.utils.Vector;
import com.blockposht.utils.random.RandomUtils;

public class MixedStrategy<E> extends Strategy<E> {
    private final RandomUtils rand;

    private List<Strategy<E>> strategies;
    private Vector p;

    private Strategy<E> current;

    public MixedStrategy(List<Strategy<E>> strategies, Vector p) {
        this(strategies, p, new Random());
    }
    
    public MixedStrategy(List<Strategy<E>> strategies, Vector p, Random rand) {
        this.rand = new RandomUtils(rand);
        this.strategies = strategies;
        this.p = p;
    }

    @Override
    public Action decide(E environment, List<Action> actions) {
        current = selectStrategy();
        return super.decide(environment, actions);
    }

    @Override
    public int evaluate(E env, Action act) {
        return current.evaluate(env, act);
    }
    
    private Strategy<E> selectStrategyUniformly() {
        var s = rand.nextInt(strategies.size());
        return strategies.get(s);
    }

    private Strategy<E> selectStrategy() {
        return strategies.get(rand.choose(p));
    }

    public void updateWeights(Vector d) {
        this.p = (p.add(d)).normalized();
    }

    public void slidingMean(Vector vec, int beta) {
        this.p = p.slidingMean(vec, beta);   
    }
}
