package view;
//String str1= JOptionPane.showInputDialog("enter this intenger");   输入衔接
//int num1=Integer.parseInt(str1);

import Asssignment4Components.*;
import controller.GameController;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class GameFrame extends JFrame {
    public static GameController controller;
    public static Game g;
    private ChessBoardPanel chessBoardPanel;
    private StatusPanel statusPanel;
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screensize = toolkit.getScreenSize();

    public GameController getController() {
        return controller;
    }

    public void setGame(Game game) {
        this.g = game;
    }

    public GameFrame(int[][] last, int bscore, int wscore, int cPlayer) {
        MainFrame.NORAMLmode=1;
        MainFrame.CHEATmode=0;
        MainFrame.AImode=0;
        if (MainFrame.gid == 1) {
            Game g = new Game("othello" + MainFrame.gid, new Player("a","password"), new Player("b","password"));
            MainFrame.gid++;
            setGame(g);
        }
        int frameSize = (int) (screensize.getHeight() * 0.8);

        this.setTitle("Let's play othello!");
        this.setLayout(null);//???

        //获取窗口边框的长度，将这些值加到主窗口大小上，这能使窗口大小和预期相符
        Insets inset = this.getInsets();
        this.setSize(frameSize + inset.left + inset.right, frameSize + inset.top + inset.bottom);

        System.out.println("gameframesize is:"+frameSize + inset.left + inset.right+"and"+frameSize + inset.top + inset.bottom);
        this.setLocationRelativeTo(null);

        chessBoardPanel = new ChessBoardPanel((int) (this.getWidth() * 0.8), (int) (this.getHeight() * 0.7), last);
        chessBoardPanel.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2, (this.getHeight() - chessBoardPanel.getHeight()) / 3);


        statusPanel = new StatusPanel((int) (this.getWidth() * 0.8), (int) (this.getHeight() * 0.1), bscore, wscore);//创建了新的标记
        statusPanel.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2, 0);
        controller = new GameController(chessBoardPanel, statusPanel);///？？？？？
        controller.setGamePanel(chessBoardPanel);//？？？？？
        controller.setCurrentPlayer(cPlayer);
        System.out.println("normal curretn player" + cPlayer);

        add(chessBoardPanel);
        this.add(statusPanel);


        JButton restartBtn = new JButton("Restart");
        restartBtn.setSize(120, 50);
        restartBtn.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2, (this.getHeight() + chessBoardPanel.getHeight()) / 2);
        add(restartBtn);
        restartBtn.setBorderPainted(false);
        restartBtn.addActionListener(e -> {
            System.out.println("click restart Btn");

            if (GameFrame.controller.canContinue()) {
                int userOption = JOptionPane.showConfirmDialog(null, "Are you sure?There is no winner yet!", "WARNING", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (userOption == JOptionPane.OK_OPTION) {

                    MainFrame.NORAMLmode=1;
                    MainFrame.CHEATmode=0;
                    int[][] first = new int[8][8];
                    for (int i = 0; i < 8; i++)
                        for (int j = 0; j < 8; j++) {
                            first[i][j] = 0;
                        }
                    first[3][3] = 1;
                    first[4][4] = 1;
                    first[4][3] = -1;
                    first[3][4] = -1;
                    GameFrame gameFrame = new GameFrame(first, 2, 2, 1);

                    JPanel panel=new JPanel();
                    JLabel img2 =new JLabel(new ImageIcon("CBP.png"));
                    panel.add(img2);
                    img2.setBounds(0, 0, 720, 720);
                    img2.setVisible(true);
                    panel.setBounds(0, 0, 720, 720);
                    gameFrame.add(panel);

                    Game g = new Game("othello" + MainFrame.gid, new Player("a","password"), new Player("b","password"));
                    MainFrame.gid++;
                    gameFrame.setGame(g);
                    gameFrame.setVisible(true);
                    this.setVisible(false);
                    try{
                        add(gameFrame);
                    }catch(Exception e1)
                    {

                    }
                }
            } else {
                MainFrame.NORAMLmode=1;
                MainFrame.CHEATmode=0;
                int[][] first = new int[8][8];
                for (int i = 0; i < 8; i++)
                    for (int j = 0; j < 8; j++) {
                        first[i][j] = 0;
                    }
                first[3][3] = 1;
                first[4][4] = 1;
                first[4][3] = -1;
                first[3][4] = -1;
                GameFrame gameFrame = new GameFrame(first, 2, 2, 1);
                JPanel panel=new JPanel();
                JLabel img2 =new JLabel(new ImageIcon("CBP.png"));
                panel.add(img2);
                img2.setBounds(0, 0, 720, 720);
                img2.setVisible(true);
                panel.setBounds(0, 0, 720, 720);
                gameFrame.add(panel);

                gameFrame.setVisible(true);
                Game g = new Game("othello" + MainFrame.gid, new Player("a","password"), new Player("b","password"));
                MainFrame.gid++;
                gameFrame.setGame(g);

                this.setVisible(false);
                try{
                    add(gameFrame);
                }catch(Exception e1)
                {

                }
            }
        });

        JButton loadGameBtn = new JButton("Load");
        loadGameBtn.setSize(120, 50);
        loadGameBtn.setLocation(restartBtn.getX() + restartBtn.getWidth() + 30, restartBtn.getY());
        add(loadGameBtn);
        loadGameBtn.setBorderPainted(false);
        loadGameBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser(".//Code//src//Saved_games");

            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            int returnVal = fileChooser.showOpenDialog(fileChooser);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                Loader2 loader2 = new Loader2();
                ArrayList<Integer>copy=loader2.load(filePath);
                ArrayList<Step>copy_2=loader2.loaded(filePath);
//                Game game = Loader.load(filePath);

                Game g = new Game("othello"+MainFrame.gid,new Player("a","111"),new Player("b","ss"));
                MainFrame.gid++;
                this.setGame(g);
                controller.readFileData(filePath);
                for(int i=0;i<copy_2.size();i++)
                {
                    g.addStep(copy_2.get(i));
                }
                this.controller.clear();
                for(int i=0;i<copy.size();i=i+3)
                {
                    this.controller.Putting(copy.get(i), copy.get(i+1),copy.get(i+2));
                    System.out.println("chess  "+copy.get(i)+"  and xx"+copy.get(i+1)+"    and yy"+copy.get(i+2));
                    this.controller.countScore();
                }
            }
            System.out.println("clicked Load Btn");

        });

        JButton saveGameBtn = new JButton("Save");
        saveGameBtn.setSize(120, 50);
        saveGameBtn.setLocation(loadGameBtn.getX() + restartBtn.getWidth() + 30, restartBtn.getY());
        add(saveGameBtn);
        saveGameBtn.setBorderPainted(false);
        saveGameBtn.addActionListener(e -> {

            JFileChooser chooser = new JFileChooser(".//Code//src//Saved_games");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "jpg", "gif");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showSaveDialog(new JPanel());
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                System.out.println("你打开的文件夹是: " +
                        chooser.getSelectedFile().getPath());
                String path = chooser.getSelectedFile().getPath();
                try {
                    File f = new File(path + ".txt");
                    System.out.println(f.getAbsolutePath());
                    f.createNewFile();
                    BufferedWriter out = new BufferedWriter(new FileWriter(f));

                    out.write(String.valueOf(this.g.toString()));
                    out.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        JButton CheatBtn = new JButton("<html>I want to<br>CHEAT</html>");
        CheatBtn.setSize(90, 100);
        CheatBtn.setLocation(saveGameBtn.getX() + loadGameBtn.getWidth() + 80, restartBtn.getY() - 360);
        add(CheatBtn);
        CheatBtn.setBorderPainted(false);
        CheatBtn.addActionListener(e -> {
            MainFrame.NORAMLmode=0;
            MainFrame.CHEATmode=1;
            CheatFrame gameFrame = new CheatFrame(chessBoardPanel.getData(), chessBoardPanel.getBlackScore(), chessBoardPanel.getWhiteScore(), controller.getCurrentPlayer());
            JPanel panel=new JPanel();
            JLabel img2 =new JLabel(new ImageIcon("CBP.png"));
            panel.add(img2);
            img2.setBounds(0, 0, 720, 720);
            img2.setVisible(true);
            panel.setBounds(0, 0, 720, 720);
            gameFrame.add(panel);
            gameFrame.setGame(this.g);
            gameFrame.setVisible(true);
            this.setVisible(false);
        });


        JButton BackBtn = new JButton("<html>CHANGE<br>MODE</html>");
        BackBtn.setSize(120, 50);
        BackBtn.setLocation(saveGameBtn.getX() + loadGameBtn.getWidth() + 30, restartBtn.getY());
        add(BackBtn);
        BackBtn.setBorderPainted(false);
        BackBtn.addActionListener(e -> {
            int userOption1;
            if (GameFrame.controller.canContinue())
            {
                userOption1 = JOptionPane.showConfirmDialog(null, "Are you sure?There is no winner yet!", "WARNING", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(userOption1==JOptionPane.OK_OPTION)
                {
                    MainFrame mainFrame = new MainFrame();
                    JPanel panel = new JPanel();
                    JLabel img2 = new JLabel(new ImageIcon("choose_Mode.JPG"));
                    panel.add(img2);
                    img2.setBounds(0, 0, 1000, 800);
                    img2.setVisible(true);
                    panel.setBounds(0, 0, 1000, 800);
                    mainFrame.add(panel);
                    mainFrame.setVisible(true);
                    this.setVisible(false);
                }
            }
        });

        JButton ExitBtn = new JButton("Exit");
        ExitBtn.setSize(90, 100);
        ExitBtn.setLocation(10,250);
        add(ExitBtn);
        ExitBtn.setBorderPainted(false);
        ExitBtn.addActionListener(e -> {
            int userOption1;
                userOption1 = JOptionPane.showConfirmDialog(null, "Are you sure to exit?", "WARNING", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(userOption1==JOptionPane.OK_OPTION)
                {
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
            }
        });

        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

}