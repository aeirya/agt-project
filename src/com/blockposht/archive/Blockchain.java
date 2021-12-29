// package com.blockposht.archive;

// import java.lang.System.Logger;
// import java.util.HashMap;
// import java.util.Map;

// import com.blockposht.blockchain.Block;
// import com.blockposht.blockchain.IBlockchain;
// import com.blockposht.sha.IHashComputer;
// import com.blockposht.sha.Sha256;

// public class Blockchain implements IBlockchain {
//     private final Map<Integer, Block> blocks;
//     private final BlockFactory blockFactory;
//     private Block genesis;
//     private Block lastAddedBlock;
//     private int date;

//     private static IHashComputer encoder = (input) -> Sha256.digest(input);

//     public Blockchain() {
//         blockFactory = new BlockFactory(encoder);
//         blocks = new HashMap<>();
//         genesis = null;
//         lastAddedBlock = null;
//         date = 0;
//     }

//     public Block getGenesisBlock() {
//         return genesis;
//     }

//     public void addBlock(int parentID, String data) {
//         if (!blocks.keySet().contains(parentID))
//             java.util.logging.Logger.getGlobal().warning("parent id doesn't exists");

//         var parent = blocks.get(parentID);
//         int id = lastAddedBlock.id + 1;
//         var block = blockFactory.createBlock(parent, id, getDate(), data);
//         parent.nextBlock = block; // todo: change this
//         blocks.
//     }

//     public void addBlock() {

//     }

//     private Block getBlock(int id) {
//         return blocks.get(id);
//     }

//     private int getDate() {
//         return date++;
//     }

//     public static void main(String[] args) {
//         var blockchain = new Blockchain();
//         var block = blockchain.getGenesisBlock();
//         var fact = new BlockFactory();
//         fact.appendBlock(ancestor, block);
//     }
// }
