package com.blockposht.blockchain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ForkfulBlockchain implements IBlockchain {
    private final List<ForkableChain> chains;

    public ForkfulBlockchain() {
        chains = new ArrayList<>();
        chains.add(new ForkableChain());
    }

    public ChainBlock getGenesis() {
        return chains.get(0).get(0);
    }

    public List<ChainBlock> get(int height) {
        return chains.stream()
            .filter(c -> c.size() > height)
            .map(c -> c.get(height))
            .collect(Collectors.toList());
    }

    public List<ChainBlock> getChainTips() {
        return chains.stream()
            .map(ForkableChain::getTip)
            .collect(Collectors.toList());
    }

    private ForkableChain getLongestChain(List<ForkableChain> chains) {
        return chains.stream()
            .reduce(chains.get(0), (c,d) -> c.size() < d.size() ? d:c);
    }
    
    public ForkableChain getLongestChain() {
        return getLongestChain(chains);
    }

    public List<ForkableChain> getNextLongestChain(ForkableChain chain) {
        var list = chains.stream()
            .filter(c -> c.size() <= chain.size() && c != chain)
            .collect(Collectors.toList());
        
        // only returning chains of the same size
        var longest = list.stream().reduce((c,d)-> c.size()>d.size()?c:d);
        // checking to turn of warning
        if (longest.isEmpty()) return new ArrayList<>();
        
        var longestLen = longest.get().size();
        return list.stream()
            .filter(c -> c.size() == longestLen)
            .collect(Collectors.toList());    
    }

    private List<ForkableChain> find(ChainBlock block) {
        return chains.stream()
            .filter(c -> c.size() > block.height)
            .filter(c -> c.contains(block))
            .collect(Collectors.toList());
    }

    public ForkableChain findOne(ChainBlock block) {
        return chains.stream()
            .filter(c -> c.contains(block))
            .findFirst()
            .orElse(null);
    }

    public ChainBlock add(ChainBlock parent, UserBlock block) {
        var found = find(parent);
        if (found.isEmpty()) return null;
        var tip = found.stream()
            .filter(c -> c.getTip().equals(parent)).findAny();
        // if found, add to tip, else fork
        if (tip.isPresent()) {
            return tip.get().add(block);
        } else {
            var fork = found.get(0).fork(parent);
            chains.add(fork);
            return fork.add(block);
        }
    }

    public void add(UserBlock block) {
        getLongestChain().add(block);
    }

    public IBlock getPredecessor(IBlock block) {
        return getPredecessor((ChainBlock) block);
    }
    
    public IBlock getPredecessor(ChainBlock block) {
        return find(block).get(0).getPredecessor(block);
    }
}
