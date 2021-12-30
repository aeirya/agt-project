package com.blockposht.blockchain;

public class BlockData {
    private final Boolean isValid;
    private final int fee;
    private final int gas;
    
    public BlockData(Boolean isValid, int fee, int gas) {
        this.isValid = isValid;
        this.fee = fee;
        this.gas = gas;
    }

    public boolean isValid() {
        return isValid;
    }

    public int getFee() {
        return fee;
    }

    public int getGas() {
        return gas;
    }

    public static final BlockData validData = new BlockData(true, 0, 0);

}
