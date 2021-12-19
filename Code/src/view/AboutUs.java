package view;

import AboutUsers.LoginFrm;
import AboutUsers.RegisterFrm;
import controller.GameController;
import javafx.scene.canvas.GraphicsContext;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class AboutUs extends JFrame {

    public AboutUs() {

        this.setTitle("RULES");
        this.setLayout(null);//???

        Insets inset = this.getInsets();
        this.setSize(1000, 580);

        this.setLocationRelativeTo(null);

        JButton ruleBtn = new JButton("BACK");
        Font font1= new Font("微软雅黑",Font.BOLD,30);
        ruleBtn.setFont(font1);
        ruleBtn.setSize(200, 100);
        ruleBtn.setLocation(760, 433);
        add(ruleBtn);
        ruleBtn.setBorderPainted(false);
        ruleBtn.addActionListener(e -> {
            RegisterFrame registerFrame = new RegisterFrame();
            JPanel panel=new JPanel();
            JLabel img2 =new JLabel(new ImageIcon("Othello22.jpg"));
            panel.add(img2);
            img2.setBounds(0, 0, 1000, 800);
            img2.setVisible(true);
            panel.setBounds(0, 0, 1000, 800);
            registerFrame.add(panel);
            registerFrame.setVisible(true);
            this.setVisible(false);

        });


    }

    public void paintComponent(Graphics g) {
        super.printComponents(g);
    }
}