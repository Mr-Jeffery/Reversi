package view;

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

public class MainFrame extends JFrame {
    public static int mode=0;
    public static int gid=1;
    public static int step=0;
    public static int getMode() {
        return mode;
    }

    private URL url2;
    private AudioClip ac2;

    public MainFrame() {

        this.setTitle("Let's Play Othello!");
        this.setLayout(null);//???

        Insets inset = this.getInsets();
        this.setSize(1000 + inset.left + inset.right, 750 + inset.top + inset.bottom);

        this.setLocationRelativeTo(null);

        File f4 = new File("Put.wav");
        try {
            url2= f4.toURL();
        } catch (
                MalformedURLException e) {
            e.printStackTrace();
        }
        ac2= Applet.newAudioClip(url2);

        JButton AIBtn = new JButton("EASY AI MODE");
        Font font1= new Font("微软雅黑",Font.BOLD,16);
        AIBtn.setFont(font1);
        AIBtn.setSize(200, 100);
        AIBtn.setLocation(75, 225);
        add(AIBtn);
        AIBtn.setBorderPainted(false);
        AIBtn.addActionListener(e -> {

            ac2.play();
        });

        JButton difAIBtn = new JButton("DIFFICULT AI MODE");
        difAIBtn.setFont(font1);
        difAIBtn.setSize(400, 100);
        difAIBtn.setLocation(0, 385);
        add(difAIBtn);
        difAIBtn.setBorderPainted(false);
        difAIBtn.addActionListener(e -> {

        ac2.play();
        });

        JButton NORMALBtn = new JButton("NORMAL START");
        NORMALBtn.setFont(font1);
        NORMALBtn.setSize(200, 100);
        NORMALBtn.setLocation(75, 570);
        add(NORMALBtn);
        NORMALBtn.setBorderPainted(false);
        NORMALBtn.addActionListener(e -> {
            ac2.play();

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

            JPanel panel=new JPanel();
            JLabel img2 =new JLabel(new ImageIcon("othello.jpg"));
            panel.add(img2);
            img2.setBounds(0, 0, 1000, 800);
            img2.setVisible(true);
            panel.setBounds(0, 0, 1000, 800);
            gameFrame.add(panel);

            gameFrame.setVisible(true);
            this.setVisible(false);
            add(gameFrame);

        });

    }

    public void paintComponent(Graphics g) {
        super.printComponents(g);
    }
}