package com.blockposht.blockchain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;


import com.blockposht.interfaces.IBlockchain;

public class Chain implements IBlockchain {

    // public interface IBlockmap extends Map<String, Block> {}; 

    protected final ArrayList<Map<String, Block>> blocks;
    protected final IBlockEncoder encoder;
    // private final Random randomGenerator;

    public Chain() {
        blocks = new ArrayList<>();
        encoder = new DummyBlockEncoder();
        // randomGenerator = new Random();
        this.createGenesisBlock();
    }

    public Block getGenesis() {
        return blocks.get(0).values().stream().findFirst().get();
    }

    private Date getTime() {
        return Calendar.getInstance().getTime();
    }

    public Block getTip() {
        return blocks.get(blocks.size()-1).values().iterator().next();
        // int i = randomGenerator.nextInt(values.length);
        // return (Block) values[i];
    }

    public Block getNextTip(Block blk) {
        // blocks.get(blk.height);
        //kfjhdfdkjhf
        return null;
    }

    public Block getPredecessor(Block blk) {
        return blocks.get(blk.height-1).get(blk.previousBlockHash);
    }

    // todo: get other tips

    private void createGenesisBlock() {
        Block blk = new Block(0, getTime(), BlockData.validData, "");
        saveBlock(blk);
    }

    private void saveBlock(Block blk) {
        if (blocks.size() <= blk.height) {
            blocks.add(blk.height, new HashMap<>());
        }
        blocks.get(blk.height).put(blk.getHash(), blk);
    }

    public void addBlock(Block parent, Block blk) {
        if (blk.height > blocks.size())  {
            Logger.getGlobal().warning(String.format("block with height %d not supported", blk.height));
            return;
        } else {
            saveBlock(blk);
        }
        blk.previousBlockHash = parent.getHash();
    }

    // public Block findFork(Block blk) {

    // }

    // public Block getNextChain(Block blk) {

    // }

    public List<Block> getNext(Block blk) {
        return blocks.get(blk.height+1)
            .values()
            .stream()
            .filter(b -> b.previousBlockHash.equals(blk.getHash()))
            .collect(Collectors.toList());
    }

    public void PrintChain() {
        System.out.println(blocks);
    }

    public static void main(String[] args) {
        System.out.println( new Chain().blocks.get(1));
    }
}
