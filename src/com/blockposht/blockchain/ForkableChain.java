package com.blockposht.blockchain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.blockposht.utils.serialize.ISerializable;
import com.blockposht.utils.serialize.ISerializer;

public class ForkableChain implements ISerializable {
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

    public int size() {
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

    public ChainBlock add(UserBlock block) {
        // add block to the end
        var cb = ChainBlock.of(block, getTip(), size());
        chain.add(cb);
        return cb;
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

    public boolean isForkOf(ForkableChain chain) {
        return forkedChain == chain;
    }

    public boolean isRecentForkOf(ForkableChain chain, int maxHeightDiffrence) {
        return isForkOf(chain) && (this.chain.size()<=maxHeightDiffrence);
    }

    public ForkableChain findCommonForkedChain(ForkableChain other) {
        // todo: test this
        if (other == this) 
            return this;
        if (other.forkedChain == this)
            return this;
        if (this.forkedChain == other)
            return this;
        if (this.forkedChainSize > other.forkedChainSize)
            return other.findCommonForkedChain(forkedChain);
        else return this.findCommonForkedChain(other.forkedChain);
    }

    public Entry<Integer, ForkableChain> findKnot(ForkableChain other) {
        if (this == other) 
            return Map.entry(size()-1, this);
        if (this.forkedChainSize > other.forkedChainSize)
            return other.findKnot(this);
        if (other.forkedChain == this)
            return Map.entry(other.forkedChainSize, this);
        return other.forkedChain.findKnot(this);
    }

    /**
     *  the height in which this chain forked started
    */
    public int getForkHeight() {
        return forkedChainSize;
    }

    @Override
    public void serialize(ISerializer ser) throws IOException {
        // todo: recheck this
        ser.serialize(chain);
    }
}

