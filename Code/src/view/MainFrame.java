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

        JButton startBtn = new JButton("Start");
        startBtn.setSize(120, 50);
        startBtn.setLocation(500,500);
        add(startBtn);
        startBtn.addActionListener(e -> {
            GameFrame gameFrame=new GameFrame(900);
            System.out.println("click restart Btn");
        });
    }


}
