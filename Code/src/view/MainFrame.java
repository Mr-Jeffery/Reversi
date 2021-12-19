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
    public static int NORAMLmode=0;
    public static int CHEATmode=0;
    public static int gid=1;
    public static int step=0;
    public static  int AImode=0;

    private URL url2;
    private AudioClip ac2;


    public MainFrame() {

        this.setTitle("Let's Play Othello!");
        this.setLayout(null);//???

        Insets inset = this.getInsets();
        this.setSize(1000 , 580);

        this.setLocationRelativeTo(null);

        File f4 = new File("Put.wav");
        try {
            url2= f4.toURL();
        } catch (
                MalformedURLException e) {
            e.printStackTrace();
        }
        ac2= Applet.newAudioClip(url2);

        JButton AIBtn = new JButton("EASY PvE");
        Font font1= new Font("微软雅黑",Font.BOLD,16);
        AIBtn.setFont(font1);
        AIBtn.setSize(200, 100);
        AIBtn.setLocation(550, 250);
        add(AIBtn);
        AIBtn.setBorderPainted(false);
        AIBtn.addActionListener(e -> {
            ac2.play();
            AImode=1;
            int userOption = JOptionPane.showConfirmDialog(null, "Do you want to be BLACK?", "SET YOUR CHESS", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
           int me=0;
            if (userOption == JOptionPane.YES_OPTION) {
                me=1;//black
            }
            else if(userOption == JOptionPane.NO_OPTION) me=-1;//white
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
            EasyAIFrame gameFrame = new EasyAIFrame(first,2,2,1,me);

            JPanel panel=new JPanel();
            JLabel img2 =new JLabel(new ImageIcon("CBP.png"));
            panel.add(img2);
            img2.setBounds(0, 0, 720, 720);
            img2.setVisible(true);
            panel.setBounds(0, 0, 720, 720);
            gameFrame.add(panel);

            this.setVisible(false);
            gameFrame.setVisible(true);
        });

        JButton difAIBtn = new JButton("HARD PvE");
        difAIBtn.setFont(font1);
        difAIBtn.setSize(200, 100);
        difAIBtn.setLocation(240, 425);
        add(difAIBtn);
        difAIBtn.setBorderPainted(false);
        difAIBtn.addActionListener(e -> {
            ac2.play();
            AImode=2;
            NORAMLmode=0;
            CHEATmode=0;
            int userOption = JOptionPane.showConfirmDialog(null, "Do you want to be BLACK?", "SET YOUR CHESS", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            int me=0;
            if (userOption == JOptionPane.YES_OPTION) {
                me=1;//black
            }
            else if(userOption == JOptionPane.NO_OPTION) me=-1;//white
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
            DifAIFrame gameFrame = new DifAIFrame(first,2,2,1,me);

            JPanel panel=new JPanel();
            JLabel img2 =new JLabel(new ImageIcon("CBP.png"));
            panel.add(img2);
            img2.setBounds(0, 0, 720, 720);
            img2.setVisible(true);
            panel.setBounds(0, 0, 720, 720);
            gameFrame.add(panel);

            this.setVisible(false);
            gameFrame.setVisible(true);
        });

        JButton NORMALBtn = new JButton("PvP");
        NORMALBtn.setFont(font1);
        NORMALBtn.setSize(200, 100);
        NORMALBtn.setLocation(330, 80);
        add(NORMALBtn);
        NORMALBtn.setBorderPainted(false);
        NORMALBtn.addActionListener(e -> {
            NORAMLmode=1;
            CHEATmode=0;
            AImode=0;
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
            JLabel img2 =new JLabel(new ImageIcon("CBP.png"));
            panel.add(img2);
            img2.setBounds(0, 0, 720, 720);
            img2.setVisible(true);
            panel.setBounds(0, 0, 720, 720);
            gameFrame.add(panel);
            this.setVisible(false);
            gameFrame.setVisible(true);
            try {
                add(gameFrame);
            }catch (IllegalArgumentException e1){
            }

        });

    }

    public void paintComponent(Graphics g) {
        super.printComponents(g);
    }
}