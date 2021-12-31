package com.blockposht.evolutionary;

import java.util.List;

public abstract class Strategy<Environment> {

    public Action decide(Environment environment, List<Action> actions) {
        int bestReward = 0;
        Action bestAction = null;
        int reward;
        for (Action act : actions) {
            reward = evaluate(environment, act);
            if (bestAction == null || reward > bestReward) {
                bestAction = act;
                bestReward = reward;
            }
        }
        return bestAction;
    }

    public abstract int evaluate(Environment env, Action act);
}
