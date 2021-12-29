package com.blockposht.blockchain.forkable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.blockposht.blockchain.BlockData;
import com.blockposht.blockchain.UserBlock;

public class ForkfulBlockchain {
    private final List<ForkableChain> chains;

    public ForkfulBlockchain() {
        chains = new ArrayList<>();
        chains.add(new ForkableChain());
    }

    public List<ChainBlock> get(int height) {
        return chains.stream()
            .filter(c -> c.size() > height)
            .map(c -> c.get(height))
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
        var longest = list.stream().reduce((c,d)->c.size()>d.size()?c:d);
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

    public void add(ChainBlock parent, UserBlock block) {
        var found = find(parent);
        if (found.isEmpty()) return;
        var tip = found.stream()
            .filter(c -> c.getTip().equals(parent)).findAny();
        // if found, add to tip, else fork
        if (tip.isPresent()) {
            tip.get().add(block);
        } else {
            var fork = found.get(0).fork(parent);
            fork.add(block);
            chains.add(fork);
        }
    }

    public void add(UserBlock block) {
        getLongestChain().add(block);
    }

    public static void main(String[] args) {
        var blockchain = new ForkfulBlockchain();
        blockchain.add(new UserBlock.Dummy());
        blockchain.add(new UserBlock.Dummy());
        blockchain.add(new UserBlock.Dummy());

        var chain = blockchain.getLongestChain();
        var prev = chain.getPredecessor(chain.getTip());
        blockchain.add(prev, new UserBlock(new BlockData(false, 2, 3)));
        System.out.println();
    }
}
