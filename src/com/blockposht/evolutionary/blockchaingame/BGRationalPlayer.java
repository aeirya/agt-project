package com.blockposht.evolutionary.blockchaingame;

import java.util.ArrayList;
import java.util.List;

import com.blockposht.blockchain.ChainBlock;
import com.blockposht.evolutionary.Action;
import com.blockposht.evolutionary.Strategy;
import com.blockposht.random.RandomUtils;
import com.blockposht.random.Vector;

public class BGRationalPlayer extends BGPlayer {
    private final List<Strategy<BlockchainGame>> strategies;
    private final Vector strgyRewards;
    private final Vector strgyTries;
    private int strategyIndex = 0;

    private final List<RationalPlayerMineAction> actions;
    private int lastCannonIndex = -1; // index to start checking

    private RandomUtils rand;
    private int round = 0;

    private float exploitivitiy = 0.8f;

    public BGRationalPlayer(int id, List<Strategy<BlockchainGame>> strategies) {
        super(id, strategies.get(0));
        this.strategies = strategies;

        strgyRewards = Vector.zeros(strategies.size());
        strgyTries = Vector.zeros(strategies.size());
        actions = new ArrayList<>();

        rand = new RandomUtils();
    }

    @Override
    public void getReward(int reward) {
        // TODO Auto-generated method stub
        // todo: remove get reward method

    }

    @Override
    public void play(BlockchainGame game) {
        this.rethink(game);

        var act = decide(game);
        remember(act);
        game.play(act, id);

        round++;
    }

    private void rethink(BlockchainGame game) {
        // evaluate reward
        for (int i=lastCannonIndex+1; i<actions.size(); ++i) {
            if (game.isCannon(actions.get(i).getChainblock())) {
                var act = actions.get(i);
                strgyRewards.set(act.strtgy, strgyRewards.get(act.strtgy) + act.getChainblock().getReward());
                lastCannonIndex = i;
            }
        }
    }

    @Override
    protected Action decide(BlockchainGame game) {
        // todo: change strategy here
        if (round < strategies.size()) {
            this.strategyIndex = round;
        } else {
            if (rand.nextDouble() <= (1-exploitivitiy)* Math.exp(-round)) {
                this.strategyIndex = rand.choose(strgyRewards.divide(strgyTries));
            } else {
                this.strategyIndex = rand.getRandom().nextInt(strategies.size());
            }
        }
        this.strategy = strategies.get(strategyIndex);

        return super.decide(game);
    }

    private void remember(Action act) {
        if (((BGAction)act).getType().equals(BGActionType.MINE)) {
            increaseStrategyTries(strategyIndex);
            actions.add(new RationalPlayerMineAction(strategyIndex, (BGActionMine)act));
        } else {
            System.out.println("oops");
        }
    }

    private void increaseStrategyTries(int strategy) {
        strgyTries.set(strategy, strgyTries.get(strategy)+1);
}

    static class RationalPlayerMineAction {
        final int strtgy;
        final BGActionMine action;

        public RationalPlayerMineAction(int strtgy, BGActionMine action) {
            this.strtgy = strtgy;
            this.action = action;
        }

        ChainBlock getChainblock() {
            return action.getChainblock();
        }

    }

}
