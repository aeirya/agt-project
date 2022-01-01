package com.blockposht.game.coingame;


public class CoinGameTest {
    public static void main(String[] args) {
        CoinGamePlayer p0, p1;
        p0 = new CoinGamePlayer(0, new CopycatPlayer(0));
        p1 = new CoinGamePlayer(1, TheifPlayer.INSTANCE);

        var game = new CoinGame(p0, p1);

        for (int round = 0; round < 10; ++round) {
            p0.play(game);
            p1.play(game);

            System.out.println(String.format("%d vs %d", p0.getMoney(), p1.getMoney()));
        }
        
    }
}
