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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class CheatFrame extends JFrame {
    public static GameController controller;
    private ChessBoardPanel chessBoardPanel;
    private StatusPanel statusPanel;
    public static Game g;
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screensize=toolkit.getScreenSize();

    public GameController getController()
    {
        return controller;
    }
    public void setGame(Game game)
    {
        this.g=game;
    }
    public CheatFrame(int[][]input,int bscore,int wscore,int cPlayer) {
        MainFrame.mode++;
        System.out.println("mode setted2");
        int frameSize= (int)(screensize.getHeight()*0.8);

        this.setTitle("Let's play othello!");
        this.setLayout(null);//???

        //获取窗口边框的长度，将这些值加到主窗口大小上，这能使窗口大小和预期相符
        Insets inset = this.getInsets();
        this.setSize(frameSize + inset.left + inset.right, frameSize + inset.top + inset.bottom);

        this.setLocationRelativeTo(null);

            chessBoardPanel = new ChessBoardPanel((int) (this.getWidth() * 0.8), (int) (this.getHeight() * 0.7),input);
            chessBoardPanel.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2, (this.getHeight() - chessBoardPanel.getHeight()) / 3);

            statusPanel = new StatusPanel((int) (this.getWidth() * 0.8), (int) (this.getHeight() * 0.1),bscore,wscore);//创建了新的标记
            statusPanel.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2, 0);
            controller = new GameController(chessBoardPanel, statusPanel);///？？？？？
            controller.setGamePanel(chessBoardPanel);//？？？？？
            controller.setCurrentPlayer(cPlayer);
            System.out.println("cheat curretn player"+cPlayer);


        add(chessBoardPanel);
            this.add(statusPanel);


            JButton restartBtn = new JButton("Restart");
            restartBtn.setSize(120, 50);
            restartBtn.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2, (this.getHeight() + chessBoardPanel.getHeight()) / 2);
            add(restartBtn);
            restartBtn.addActionListener(e -> {
              if(CheatFrame.controller.canContinue()) {
                    int userOption = JOptionPane.showConfirmDialog(null, "Are you sure?There is no winner yet!", "WARNING", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(userOption==JOptionPane.OK_OPTION)
                    {
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
                        GameFrame gameFrame = new GameFrame(first,2,2,1);

                        Game g=new Game("othello"+MainFrame.gid,new Player("Alice","123"),new Player("Bob","123"));
                        MainFrame.gid++;
                        gameFrame.setGame(g);

                        this.setVisible(false);
                        add(gameFrame);
                    }
                }
                else
                {
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
                    GameFrame gameFrame = new GameFrame(first,2,2,1);

                    Game g=new Game("othello"+MainFrame.gid,new Player("Alice","123"),new Player("Bob","123"));
                    MainFrame.gid++;
                    gameFrame.setGame(g);

                    this.setVisible(false);
                    add(gameFrame);
                }
            });

            JButton loadGameBtn = new JButton("Load");
            loadGameBtn.setSize(120, 50);
            loadGameBtn.setLocation(restartBtn.getX()+restartBtn.getWidth()+30, restartBtn.getY());
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
        });

        JButton CheatBtn = new JButton("<html>back to<br>NORMAL</html>");
        CheatBtn.setSize(90, 100);
        CheatBtn.setLocation(saveGameBtn.getX()+loadGameBtn.getWidth()+90, restartBtn.getY()-500);
        add(CheatBtn);
        CheatBtn.addActionListener(e -> {
            GameFrame gameFrame= new GameFrame(chessBoardPanel.getData(),chessBoardPanel.getBlackScore(),chessBoardPanel.getWhiteScore(),controller.getCurrentPlayer());
           gameFrame.setGame(this.g);
            this.setVisible(false);
            add(gameFrame);
        });

        JButton UndoBtn = new JButton("UNDO");
        UndoBtn.setSize(90, 100);
        UndoBtn.setLocation(saveGameBtn.getX() + loadGameBtn.getWidth() + 100, restartBtn.getY() - 400);
        add(UndoBtn);
        UndoBtn.addActionListener(e -> {
            int length=this.g.getStepList().size();
            int [][]board_copy=new int[8][8];
            for(int i=0;i<8;i++)
                for(int j=0;j<8;j++)
                {
                    board_copy[i][j]=this.g.getStepList().get(length-2).getBoard()[i][j];
                }
            getController().UNDO(board_copy,length-1);
            System.out.println("this is the step that should be shown::");
            for(int i=0;i<8;i++)
            {
                for(int j=0;j<8;j++)
                    System.out.printf(" %d",this.g.getStepList().get(this.g.getStepList().size()-2).getBoard()[i][j]);
                System.out.println();
            }
            System.out.println("over");
            this.g.getStepList().remove(length-1);

        });

        JButton CHEATBtn = new JButton("CHEAT");
        CHEATBtn.setSize(90, 100);
        CHEATBtn.setLocation(saveGameBtn.getX()+loadGameBtn.getWidth()+90, restartBtn.getY()-300);
        add(CHEATBtn);
        CHEATBtn.addActionListener(e -> {
            controller.swapPlayer();
            System.out.println("CHEAT CHEAT      iiiiiiiî111ss1s1s1s1s");
        });

        this.setVisible(true);
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }
}
