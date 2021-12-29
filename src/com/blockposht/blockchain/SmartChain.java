package com.blockposht.blockchain;

public class SmartChain extends Chain {
    
    public boolean checkIsValid(Block block) {
        return block.data.isValid();
    }
}
