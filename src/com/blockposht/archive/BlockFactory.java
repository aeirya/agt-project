// package com.blockposht.archive;

// import java.util.function.Consumer;

// import com.blockposht.blockchain.Block;
// import com.blockposht.sha.IHashComputer;

// public class BlockFactory {
//     private IHashComputer encoder;

//     BlockFactory(IHashComputer encoder) {
//         this.encoder = encoder;
//     }

//     Block createGenesisBlock(String data) {
//         return new Block(0, 0, data, "");
//     }

//     Block createBlock(Block parent, int id, int date, String data) {
//         return new Block(id, date, data, 
//             encoder.computeHash(parent.toString())
//         );
//     }

// }
