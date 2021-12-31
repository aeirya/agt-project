package com.blockposht.archive.blockchain2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.blockposht.blockchain.BlockData;

public class Chain  {

    protected final ArrayList<Map<String, Block>> blocks;
    protected final IBlockEncoder encoder;

    public Chain() {
        blocks = new ArrayList<>();
        encoder = new DummyBlockEncoder();
        this.createGenesisBlock();
    }

    public Block getGenesis() {
        return blocks.get(0).values().stream().findFirst().orElse(null);
    }

    private Date getTime() {
        return Calendar.getInstance().getTime();
    }

    public Block getTip() {
        return blocks.get(blocks.size()-1).values().iterator().next();
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
            var warn = String.format("block with height %d not supported", blk.height);
            Logger.getGlobal().warning(warn);
            return;
        } else {
            saveBlock(blk);
        }
        blk.previousBlockHash = parent.getHash();
    }

    public List<Block> getNext(Block blk) {
        return blocks.get(blk.height+1)
            .values()
            .stream()
            .filter(b -> b.previousBlockHash.equals(blk.getHash()))
            .collect(Collectors.toList());
    }
}
