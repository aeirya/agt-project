package com.blockposht.archive.blockchain2;

public class SmartChain extends Chain {
    
    public boolean checkIsValid(Block block) {
        return block.data.isValid();
    }
}
