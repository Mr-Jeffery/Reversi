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

public class RegisterFrame extends JFrame {

    private URL url1;
    private AudioClip ac1;

    public RegisterFrame() {

        this.setTitle("Let's play othello!");
        this.setLayout(null);//???

        File f1 = new File("StartSound.wav");
        try {
            url1= f1.toURL();
        } catch (
                MalformedURLException e) {
            e.printStackTrace();
        }
        ac1= Applet.newAudioClip(url1);
        ac1.play();
      //  ac.wait();
        System.out.println("tried to have music");

        Insets inset = this.getInsets();
        this.setSize(1000, 580);

        this.setLocationRelativeTo(null);

        JButton ruleBtn = new JButton("GAME RULE");
        Font font1= new Font("微软雅黑",Font.BOLD,20);
        ruleBtn.setFont(font1);
        ruleBtn.setSize(200, 100);
        ruleBtn.setLocation(95, 95);
        add(ruleBtn);
        ruleBtn.setBorderPainted(false);
        ruleBtn.addActionListener(e -> {


        });

        JButton aboutUs = new JButton("ABOUT US");
        aboutUs.setFont(font1);
        aboutUs.setSize(200, 100);
        aboutUs.setLocation(700, 100);
        add(aboutUs);
        aboutUs.setBorderPainted(false);
        aboutUs.addActionListener(e -> {


        });

        JButton logIn = new JButton("LOG IN");
        logIn.setFont(font1);
        logIn.setSize(200, 100);
        logIn.setLocation(90, 340);
        add(logIn);
        logIn.setBorderPainted(false);
        logIn.addActionListener(e -> {

            LoginFrm frm = new LoginFrm("LOG IN");
            frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frm.setBounds(300, 300, 500, 500);
            frm.setVisible(true);
            this.setVisible(false);


//            MainFrame mainFrame = new MainFrame();
//            JPanel panel=new JPanel();
//            JLabel img2 =new JLabel(new ImageIcon("choose_Mode.JPG"));
//            panel.add(img2);
//            img2.setBounds(0,0, 1000, 800);
//            img2.setVisible(true);
//            panel.setBounds(0, 0, 1000, 800);
//            mainFrame.add(panel);
//            mainFrame.setVisible(true);
//            this.setVisible(false);
        });

        JButton registerBtn = new JButton("REGISTER");
        registerBtn.setFont(font1);
        registerBtn.setSize(200, 95);
        registerBtn.setLocation(700, 350);
        add(registerBtn);
        registerBtn.setBorderPainted(false);
        registerBtn.addActionListener(e -> {

            RegisterFrm frm = new RegisterFrm("REGISTER");

            frm.setVisible(true);
            frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frm.setBounds(300, 300, 500, 500);
            this.setVisible(false);
        });
    }

    public void paintComponent(Graphics g) {
        super.printComponents(g);
    }
}