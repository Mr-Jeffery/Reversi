import Asssignment4Components.Game;
import Asssignment4Components.Player;
import Asssignment4Components.Saver;
import Asssignment4Components.Step;
import java.io.*;
import java.util.Arrays;

public class Save_Load {
    public static void main(String[] args){
        Player p1 = new Player("Alice");
        Player p2 = new Player("Bob");
        Game game = new Game("Test",p1,p2);
        Step step = new Step(-1,3,3);
        game.addStep(step);
        Saver.canSave(game);
        System.out.println(String.format("%s\n%s\n%s\n%s\n%s\n%s\n",
                game.getName(),
                game.getGid(),
                game.getWhitePlayer(),
                game.getBlackPlayer(),
                game.getStepList(),
                Arrays.deepToString(game.getBoard())
                        .replace("],","]\n")));
    }
}



