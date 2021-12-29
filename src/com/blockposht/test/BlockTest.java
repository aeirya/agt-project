package com.blockposht.test;

import com.blockposht.blockchain.Block;
import com.blockposht.blockchain.DummyBlock;
import com.blockposht.blockchain.SmartChain;

public class BlockTest {
    public static void main(String[] args) {
        SmartChain chain = new SmartChain();
        var gen = chain.getGenesis();
        Block testBlock = new DummyBlock(1, gen.getHash());
        chain.addBlock(gen, testBlock);
        Block testBlock2 = new DummyBlock(2, testBlock.getHash());
        chain.addBlock(testBlock, testBlock2);
        Block forkBlock = new DummyBlock(1, gen.getHash());
        chain.addBlock(gen, forkBlock);

        chain.PrintChain();
    }
}
