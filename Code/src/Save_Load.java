import AI.Runner;
import Asssignment4Components.Game;
import Asssignment4Components.Player;
import Asssignment4Components.Saver;
import Asssignment4Components.Step;

import java.util.Arrays;

public class Save_Load {
    public static void main(String[] args){
        Runner runner = new Runner();
        Player p1 = new Player("Alice");
        Player p2 = new Player("Bob");
        Game game = new Game("Test",p1,p2);
        runner.setBoard(game.getBoard());
        Step step1 = new Step(-1,3,2);
        runner.put(-1,3,2);
        step1.setBoard(runner.getBoard());
        game.addStep(step1);
        Step step2 = new Step(1,2,4);
        runner.put(1,2,4);
        step2.setBoard(runner.getBoard());
        game.addStep(step2);
        Saver.save(game);
        System.out.println(game);
    }
}



