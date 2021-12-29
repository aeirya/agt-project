package com.blockposht.blockchain.forkable;

import java.util.ArrayList;
import java.util.List;

import com.blockposht.blockchain.UserBlock;

public class ForkableChain {
    private final ForkableChain forkedChain;
    private final int forkedChainSize;

    private final List<ChainBlock> chain;
    
    ForkableChain() {
        this(null);
        chain.add(ChainBlock.genesis());
    }

    ForkableChain(ForkableChain forkedChain) {
        this(
            forkedChain,
            forkedChain == null ? 0 : forkedChain.size()
        );
    }

    ForkableChain(ForkableChain forkedChain, int forkedChainSize) {
        chain = new ArrayList<>();
        this.forkedChain = forkedChain;
        this.forkedChainSize = forkedChainSize;  
    }

    int size() {
        return chain.size() + forkedChainSize;
    }  

    public ChainBlock get(int height) {
        if (height < 0) return null;
        
        int i = height - forkedChainSize;
        if (i < 0) {
            if (forkedChain == null) return null;
            return forkedChain.get(height);
        }
        if (i >= chain.size()) return null;
        return chain.get(i);
    }

    public ChainBlock getTip() {
        return get(size()-1);
    }

    public ChainBlock getPredecessor(ChainBlock block) {
        return get(block.height-1);
    }

    public void add(UserBlock block) {
        // add block to the end
        chain.add(ChainBlock.of(block, getTip(), size()));

    }

    public ForkableChain fork(ChainBlock block) {
        return new ForkableChain(this, block.height+1);
    }

    public ForkableChain fork() {
        return new ForkableChain(this);
    }

    public boolean contains(ChainBlock block) {
        return block.equals(get(block.height));
    }
}

