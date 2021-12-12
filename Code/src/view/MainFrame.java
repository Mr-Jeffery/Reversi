package view;

import controller.GameController;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{

    public static int gameMode;
    public static int getGameMode()
    {
        return gameMode;
    }

    public MainFrame()
    {
        this.setTitle("Let's play othello!");
        this.setLayout(null);//???

        Insets inset = this.getInsets();
        this.setSize(900 + inset.left + inset.right, 900 + inset.top + inset.bottom);

        this.setLocationRelativeTo(null);

        JButton AIBtn = new JButton("EASY AI mode");
        AIBtn.setSize(200, 100);
        AIBtn.setLocation(100,100);
        add(AIBtn);
        AIBtn.addActionListener(e -> {
            this.gameMode=3;
            /*GameFrame gameFrame=new GameFrame();
            this.setVisible(false);
            add(gameFrame);
            System.out.println("click AI Btn");*/
        });

        JButton difAIBtn = new JButton("DIFFICULT AI mode");
        difAIBtn.setSize(200, 100);
        difAIBtn.setLocation(100,225);
        add(difAIBtn);
        difAIBtn.addActionListener(e -> {
           /* this.gameMode=4;
            GameFrame gameFrame=new GameFrame();
            this.setVisible(false);
            add(gameFrame);
            System.out.println("click cheatAI Btn");*/
        });

        JButton NORMALBtn = new JButton("NORMAL mode");
        NORMALBtn.setSize(200, 100);
        NORMALBtn.setLocation(100,350);
        add(NORMALBtn);
        NORMALBtn.addActionListener(e -> {
            this.gameMode=1;
            System.out.println("normal button clicke!!!!"+this.gameMode);
            GameFrame gameFrame=new GameFrame();
            this.setVisible(false);
            add(gameFrame);
            System.out.println("click NORMAL Btn");
        });

        JButton CHEATBtn = new JButton("CHEAT mode");
        CHEATBtn.setSize(200, 100);
        CHEATBtn.setLocation(100,475);
        add(CHEATBtn);
       CHEATBtn.addActionListener(e -> {
           this.gameMode=2;
            CheatFrame gameFrame=new CheatFrame();
            this.setVisible(false);
            add(gameFrame);
            System.out.println("click CHEAT Btn");
        });
    }
}
