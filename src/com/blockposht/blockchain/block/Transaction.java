package com.blockposht.blockchain.block;

import java.util.ArrayList;
import java.util.List;

public class Transaction {
    public final List<Entity> from;
    public final List<Entity> to;

    public Transaction() {
        from = new ArrayList<>();
        to = new ArrayList<>();
    }

    public Transaction addSender(int id, float cash, float fee) {
        from.add(new Entity(id, cash, fee));
        return this;
    }
    
    public Transaction addReceiver(int id, float cash) {
        to.add(new Entity(id, cash, 0));
        return this;   
    }

    public float fee() {
        return from.stream().map(e -> e.fee).reduce(0.0f, (a,b)-> a+b);
    }


    public static class Entity {
        public final int userID;
        public final float cash;
        public final float fee;

        private Entity(int userID, float cash, float fee) {
            this.userID = userID;
            this.cash = cash;
            this.fee = fee;
        }
    }
}
