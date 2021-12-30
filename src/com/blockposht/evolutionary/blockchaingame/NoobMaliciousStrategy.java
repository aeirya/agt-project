package com.blockposht.evolutionary.blockchaingame;

import com.blockposht.evolutionary.Action;
import com.blockposht.evolutionary.Strategy;

/*
    a miner who only continues the longest chain, eyes closed
*/
public class NoobMaliciousStrategy extends Strategy<BlockchainGame>
 {

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
        // todo: check topol! fees
        var tip = env.getMainChainTip();
        if (tip.equals(act.getParent())) return 1 * act.getBlock().getData().getFee();
        return 0;
    }
    
}