package view;

import controller.GameController;
import javafx.scene.canvas.GraphicsContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
    public static int mode=0;
    public static int getMode() {
        return mode;
    }

    public MainFrame() {

        this.setTitle("Let's play othello!");
        this.setLayout(null);//???

        Insets inset = this.getInsets();
        this.setSize(900 + inset.left + inset.right, 900 + inset.top + inset.bottom);

        this.setLocationRelativeTo(null);

        JButton AIBtn = new JButton("EASY AI");
        AIBtn.setSize(200, 100);
        AIBtn.setLocation(100, 100);
        add(AIBtn);
        AIBtn.addActionListener(e -> {
            /*GameFrame gameFrame=new GameFrame();
            this.setVisible(false);
            add(gameFrame);
            System.out.println("click AI Btn");*/
        });

        JButton difAIBtn = new JButton("DIFFICULT AI");
        difAIBtn.setSize(200, 100);
        difAIBtn.setLocation(100, 225);
        add(difAIBtn);
        difAIBtn.addActionListener(e -> {
           /*
            GameFrame gameFrame=new GameFrame();
            this.setVisible(false);
            add(gameFrame);
            System.out.println("click cheatAI Btn");*/
        });

        JButton NORMALBtn = new JButton("START GAME");
        NORMALBtn.setSize(200, 100);
        NORMALBtn.setLocation(100, 350);
        add(NORMALBtn);
        NORMALBtn.addActionListener(e -> {
            int[][]first=new int[8][8];
            for(int i=0;i<8;i++)
                for(int j=0;j<8;j++)
                {
                    first[i][j]=0;
                }
            first[3][3]=1;
            first[4][4]=1;
            first[4][3]=-1;
            first[3][4]=-1;
            System.out.println("Btn reacted!!!!");
            GameFrame gameFrame = new GameFrame(first,2,2,1);
            this.setVisible(false);
            add(gameFrame);

        });

//        JButton CHEATBtn = new JButton("START GAME");
//        CHEATBtn.setSize(200, 100);
//        CHEATBtn.setLocation(100, 475);
//        add(CHEATBtn);
//        CHEATBtn.addActionListener(e -> {
//            this.gameMode = 2;
//            CheatFrame gameFrame = new CheatFrame();
//            this.setVisible(false);
//            add(gameFrame);
//            System.out.println("click CHEAT Btn");
//        });

        /*Label img2 =new JLabel(new ImageIcon("othello.png"));
        add(img2);
        img2.setBounds(0, 0, 1000, 580);
        img2.setVisible(true);*/



    }

    public void paintComponent(Graphics g) {
        super.printComponents(g);
    }
}