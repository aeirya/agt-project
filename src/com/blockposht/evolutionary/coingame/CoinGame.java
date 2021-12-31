package com.blockposht.evolutionary.coingame;

import java.util.ArrayList;
import java.util.List;

import com.blockposht.evolutionary.Action;
import com.blockposht.evolutionary.IGame;

public class CoinGame implements IGame {
    
    private final ArrayList<Action> player0Hist;
    private final ArrayList<Action> player1Hist;
    private final ArrayList<Action> actions;
    private final CoinGamePlayer p0;
    private final CoinGamePlayer p1;

    private Boolean roundFinish;

    public CoinGame(CoinGamePlayer p0, CoinGamePlayer p1) {
        player0Hist = new ArrayList<>();
        player1Hist = new ArrayList<>();
        actions = new ArrayList<>();
        roundFinish = false;

        this.p0 = p0;
        this.p1 = p1;
        actions.add(CoinGameAction.HONEST);
        actions.add(CoinGameAction.STEAL);
    }

    public Action enemyLastAction(int myID) {
        var hist = enemyHistory(myID);
        if (hist.isEmpty()) 
            return null;
        return hist.get(hist.size()-1);
    }

    public List<Action> enemyHistory(int myID) {
        if (myID == 0) return player1Hist;
        else return player0Hist;
    }

    public void play(Action action, int player) {
        if (player == 0) {
            player0Hist.add(action);
            roundFinish = false;
        } else {
            player1Hist.add(action);
            roundFinish = true;
        }
        if (roundFinish) {
            rewardPlayers();
        }
    }

    private void rewardPlayers() {
        var p0Action = (CoinGameAction) enemyLastAction(1);
        var p1Action = (CoinGameAction) enemyLastAction(0);

        if (p0Action.isHonest()) {
            if (p1Action.isHonest()) {
                p0.getReward(2);
                p1.getReward(2);
            } else {
                p0.getReward(-1);
                p1.getReward(3);
            }
        } else {
            if (!p1Action.isHonest()) {
                p0.getReward(0);
                p1.getReward(0);
            } else {
                p0.getReward(3);
                p1.getReward(-1);
            }
        }
    }

    public List<Action> getActions(int player) {
        return actions;
    }

}
