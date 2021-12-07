package view;

import controller.GameController;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{

    public MainFrame()
    {
        this.setTitle("Let's play othello!");
        this.setLayout(null);//???

        Insets inset = this.getInsets();
        this.setSize(900 + inset.left + inset.right, 900 + inset.top + inset.bottom);

        this.setLocationRelativeTo(null);

        JButton AIBtn = new JButton("AI mode");
        AIBtn.setSize(200, 100);
        AIBtn.setLocation(225,200);
        add(AIBtn);
        AIBtn.addActionListener(e -> {
            GameFrame gameFrame=new GameFrame(900);
            this.setVisible(false);
            add(gameFrame);
            System.out.println("click start Btn");
        });

        JButton NORMALBtn = new JButton("NORMAL mode");
        NORMALBtn.setSize(200, 100);
        NORMALBtn.setLocation(225,325);
        add(NORMALBtn);
        NORMALBtn.addActionListener(e -> {
            GameFrame gameFrame=new GameFrame(900);
            this.setVisible(false);
            add(gameFrame);
            System.out.println("click start Btn");
        });

        JButton CHEATBtn = new JButton("NORMAL mode");
        CHEATBtn.setSize(200, 100);
        CHEATBtn.setLocation(225,450);
        add(CHEATBtn);
       CHEATBtn.addActionListener(e -> {
            GameFrame gameFrame=new GameFrame(900);
            this.setVisible(false);
            add(gameFrame);
            System.out.println("click start Btn");
        });
    }


}
