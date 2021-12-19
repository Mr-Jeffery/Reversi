package view;

import AI.*;
import Asssignment4Components.*;

public class testAiPlayer {
    public static void main(String[] args){
        Runner runner = new Runner();
        Player p1 = new Player("Alice","123");
        Player p2 = new Player("Bob","123");
        Game game = new Game("Test",p1,p2);
        runner.setBoard(game.getBoard());
        Step step1 = new Step(-1,3,2,runner.getBoard());
        runner.put(-1,3,2);
        game.addStep(step1);
        Step step2 = new Step(1,2,4,runner.getBoard());
        runner.put(1,2,4);
        game.addStep(step2);
        AI ai = new AI();
        ai.setLayerTotal(5);
        int [][] board = step2.getBoard();
        int[] result = ai.play(board,1);
        for(int r : result){
            System.out.println(r);
        }
    }
}
