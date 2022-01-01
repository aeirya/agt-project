// package com.blockposht.test;

// import com.blockposht.blockchain.ForkfulBlockchain;
// import com.blockposht.blockchain.UserBlock;

// public class ForkableChainTest {
//     public static void main(String[] args) {
//         ForkfulBlockchain bc = new ForkfulBlockchain();
//         var blk = new UserBlock.Dummy();
//         new ForkableChainTest().test2(bc, blk);
//     }
    
//     void test2(ForkfulBlockchain bc, UserBlock blk) {
//         bc.add(blk);
//         var p = bc.add(bc.getOne(0), blk);
//         bc.add(p, blk);
//         var pp = bc.add(p, blk);
//         bc.add(p, blk);
//         bc.add(pp, blk);
//         var C= bc.getChains().stream().filter(c -> c.getForkHeight() != 0).reduce((x,y) ->x.findCommonForkedChain(y)).get();
//         for (var c1 : bc.getChains()) {
//             for (var c2 : bc.getChains()) {
//                 var D = c1.findKnot(c2);
//                 System.out.println();
//             }
//         }
//         System.out.println();
//     }
    
//     void test1(ForkfulBlockchain bc, UserBlock blk) {
//         var p = bc.add(blk);
//         bc.add(blk);
//         bc.add(p, blk);
//         var c = bc.getChains().get(0).add(blk);
//         bc.getChains().get(0).add(blk);
//         bc.add(c, blk);
//         var cfc = bc.getChains().stream().reduce((x,y)->x.findCommonForkedChain(y)).get();
//         var h = cfc.getForkHeight();
//         System.out.println();
//     }
// }
