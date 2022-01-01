package com.blockposht.game.blockchaingame;

import com.blockposht.game.Action;
import com.blockposht.game.Strategy;

/**
    a miner who only continues the longest chain, eyes closed
*/
public class NoobMaliciousStrategy extends Strategy<BlockchainGame> {
    @Override
    public int evaluate(BlockchainGame env, Action act) {
        // todo: implement attack action
        var type = ((BGAction) act).getType();
        if (type == BGActionType.MINE) {
            return evaluate(env, (BGActionMine) act);
        }
        return 0;
    }

    private int evaluate(BlockchainGame env, BGActionMine act) {
        var tip = env.getMainChainTip();
        if (tip.equals(act.getParent())) return 1 * act.getBlock().getReward();
        return 0;
    }
}