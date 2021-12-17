package view;
//String str1= JOptionPane.showInputDialog("enter this intenger");   输入衔接
//int num1=Integer.parseInt(str1);

import Asssignment4Components.Game;
import Asssignment4Components.Loader;
import Asssignment4Components.Player;
import Asssignment4Components.Saver;
import controller.GameController;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;

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
        MainFrame.mode++;
        if (MainFrame.gid == 1) {
            Game g = new Game("othello" + MainFrame.gid, new Player("a"), new Player("b"));
            MainFrame.gid++;
            setGame(g);
        }
        int frameSize = (int) (screensize.getHeight() * 0.8);

        this.setTitle("Let's play othello!");
        this.setLayout(null);//???

        //获取窗口边框的长度，将这些值加到主窗口大小上，这能使窗口大小和预期相符
        Insets inset = this.getInsets();
        this.setSize(frameSize + inset.left + inset.right, frameSize + inset.top + inset.bottom);

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
        restartBtn.addActionListener(e -> {
            System.out.println("click restart Btn");

            if (GameFrame.controller.canContinue()) {
                int userOption = JOptionPane.showConfirmDialog(null, "Are you sure?There is no winner yet!", "WARNING", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (userOption == JOptionPane.OK_OPTION) {

                    MainFrame.mode++;
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

                    Game g = new Game("othello" + MainFrame.gid, new Player("a"), new Player("b"));
                    MainFrame.gid++;
                    gameFrame.setGame(g);

                    this.setVisible(false);
                    add(gameFrame);
                }
            } else {
                MainFrame.mode++;
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

                Game g = new Game("othello" + MainFrame.gid, new Player("a"), new Player("b"));
                MainFrame.gid++;
                gameFrame.setGame(g);

                this.setVisible(false);
                add(gameFrame);
            }
        });

        JButton loadGameBtn = new JButton("Load");
        loadGameBtn.setSize(120, 50);
        loadGameBtn.setLocation(restartBtn.getX() + restartBtn.getWidth() + 30, restartBtn.getY());
        add(loadGameBtn);
        loadGameBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            int returnVal = fileChooser.showOpenDialog(fileChooser);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
//              System.out.println(filePath);
                Game game = Loader.load(filePath);
                controller.readFileData(filePath);


            }

            System.out.println("clicked Load Btn");
//                String filePath = JOptionPane.showInputDialog(this, "input the path here");
//                controller.readFileData(filePath);
        });

        JButton saveGameBtn = new JButton("Save");
        saveGameBtn.setSize(120, 50);
        saveGameBtn.setLocation(loadGameBtn.getX() + restartBtn.getWidth() + 30, restartBtn.getY());
        add(saveGameBtn);
        saveGameBtn.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "jpg", "gif");
//设置文件类型
            chooser.setFileFilter(filter);
//打开选择器面板
            int returnVal = chooser.showSaveDialog(new JPanel());
//保存文件从这里入手，输出的是文件名
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
// TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

//                System.out.println("clicked Save Btn");
//                String filePath = JOptionPane.showInputDialog(this, "input the path here");
//                controller.writeDataToFile(filePath);
        });

        JButton CheatBtn = new JButton("<html>I want to<br>CHEAT</html>");
        CheatBtn.setSize(90, 100);
        CheatBtn.setLocation(saveGameBtn.getX() + loadGameBtn.getWidth() + 100, restartBtn.getY() - 400);
        add(CheatBtn);
        CheatBtn.addActionListener(e -> {
            CheatFrame gameFrame = new CheatFrame(chessBoardPanel.getData(), chessBoardPanel.getBlackScore(), chessBoardPanel.getWhiteScore(), controller.getCurrentPlayer());
            gameFrame.setGame(this.g);
            this.setVisible(false);
            add(gameFrame);
        });
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
    public static Frame getFrame() {
        Window[] allWindows = Window.getWindows();

        int frameCount = 0;
        for (Window w : allWindows) {
            if (w instanceof Frame) {
                frameCount++;
            }
        }

        Frame[] frames = new Frame[frameCount];
        int c = 0;
        for (Window w : allWindows) {
            if (w instanceof Frame) {
                frames[c++] = (Frame)w;
            }
        }

        return frames[frameCount-2];
    }
}