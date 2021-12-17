package view;

import controller.GameController;
import javafx.scene.canvas.GraphicsContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterFrame extends JFrame {

    public RegisterFrame() {

        this.setTitle("Let's play othello!");
        this.setLayout(null);//???

        Insets inset = this.getInsets();
        this.setSize(900 + inset.left + inset.right+100, 600 + inset.top + inset.bottom);

        this.setLocationRelativeTo(null);

        JButton ruleBtn = new JButton("GAME RULE");
        ruleBtn.setSize(200, 100);
        ruleBtn.setLocation(100, 100);
        add(ruleBtn);
        ruleBtn.addActionListener(e -> {

        });

        JButton aboutUs = new JButton("ABOUT US");
        aboutUs.setSize(200, 100);
        aboutUs.setLocation(700, 100);
        add(aboutUs);
        aboutUs.addActionListener(e -> {

        });

        JButton logIn = new JButton("LOG IN");
        logIn.setSize(200, 100);
        logIn.setLocation(100, 350);
        add(logIn);
        logIn.addActionListener(e -> {
            MainFrame mainFrame = new MainFrame();
            JPanel panel=new JPanel();
            JLabel img2 =new JLabel(new ImageIcon("othello.jpg"));
            panel.add(img2);
            img2.setBounds(0, 0, 1000, 800);
            img2.setVisible(true);
            panel.setBounds(0, 0, 1000, 800);
            mainFrame.add(panel);
            mainFrame.setVisible(true);
        });

        JButton registerBtn = new JButton("REGISTER");
        registerBtn.setSize(200, 100);
        registerBtn.setLocation(700, 350);
        add(registerBtn);
        registerBtn.addActionListener(e -> {

        });
    }

    public void paintComponent(Graphics g) {
        super.printComponents(g);
    }
}