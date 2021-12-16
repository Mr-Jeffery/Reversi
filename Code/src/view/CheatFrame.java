package view;
//String str1= JOptionPane.showInputDialog("enter this intenger");   输入衔接
//int num1=Integer.parseInt(str1);

import controller.GameController;

import javax.swing.*;
import java.awt.*;

public class CheatFrame extends JFrame {
    public static GameController controller;
    private ChessBoardPanel chessBoardPanel;
    private StatusPanel statusPanel;
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screensize=toolkit.getScreenSize();

    public GameController getController()
    {
        return controller;
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

            chessBoardPanel = new ChessBoardPanel((int) (this.getWidth() * 0.8), (int) (this.getHeight() * 0.7),input,2);
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
                    this.setVisible(false);
                    add(gameFrame);
                }
            });

            JButton loadGameBtn = new JButton("Load");
            loadGameBtn.setSize(120, 50);
            loadGameBtn.setLocation(restartBtn.getX()+restartBtn.getWidth()+30, restartBtn.getY());
            add(loadGameBtn);
            loadGameBtn.addActionListener(e -> {

                System.out.println("clicked Load Btn");
                String filePath = JOptionPane.showInputDialog(this, "input the path here");
                controller.readFileData(filePath);
            });

            JButton saveGameBtn = new JButton("Save");
            saveGameBtn.setSize(120, 50);
            saveGameBtn.setLocation(loadGameBtn.getX()+restartBtn.getWidth()+30, restartBtn.getY());
            add(saveGameBtn);
            saveGameBtn.addActionListener(e -> {
                System.out.println("clicked Save Btn");
                String filePath = JOptionPane.showInputDialog(this, "input the path here");
                controller.writeDataToFile(filePath);
            });

        JButton whiteBtn = new JButton("<html>CHANGE<br>COLOR</html>");
        whiteBtn.setSize(90, 100);
        whiteBtn.setLocation(saveGameBtn.getX()+loadGameBtn.getWidth()+90, restartBtn.getY()-300);
        add(whiteBtn);
        whiteBtn.addActionListener(e -> {
            controller.swapPlayer();
        });

        JButton CheatBtn = new JButton("<html>back to<br>NORMAL</html>");
        CheatBtn.setSize(90, 100);
        CheatBtn.setLocation(saveGameBtn.getX()+loadGameBtn.getWidth()+90, restartBtn.getY()-500);
        add(CheatBtn);
        CheatBtn.addActionListener(e -> {
            GameFrame gameFrame= new GameFrame(chessBoardPanel.getBoard(),chessBoardPanel.getBlackScore(),chessBoardPanel.getWhiteScore(),controller.getCurrentPlayer());
            this.setVisible(false);
            add(gameFrame);
        });

            this.setVisible(true);
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }
}
