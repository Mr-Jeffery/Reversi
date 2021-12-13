package view;
//String str1= JOptionPane.showInputDialog("enter this intenger");   输入衔接
//int num1=Integer.parseInt(str1);

import controller.GameController;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public static GameController controller;
    private ChessBoardPanel chessBoardPanel;
    private StatusPanel statusPanel;
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screensize=toolkit.getScreenSize();

    public GameController getController()
    {
        return controller;
    }
    public GameFrame() {
        System.out.println("mode setted2");
        int frameSize= (int)(screensize.getHeight()*0.8);

        this.setTitle("Let's play othello!");
        this.setLayout(null);//???

        //获取窗口边框的长度，将这些值加到主窗口大小上，这能使窗口大小和预期相符
        Insets inset = this.getInsets();
        this.setSize(frameSize + inset.left + inset.right, frameSize + inset.top + inset.bottom);

        this.setLocationRelativeTo(null);

            chessBoardPanel = new ChessBoardPanel((int) (this.getWidth() * 0.8), (int) (this.getHeight() * 0.7));
            chessBoardPanel.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2, (this.getHeight() - chessBoardPanel.getHeight()) / 3);

            statusPanel = new StatusPanel((int) (this.getWidth() * 0.8), (int) (this.getHeight() * 0.1));//创建了新的标记
            statusPanel.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2, 0);
            controller = new GameController(chessBoardPanel, statusPanel);///？？？？？
            controller.setGamePanel(chessBoardPanel);//？？？？？

            add(chessBoardPanel);
            this.add(statusPanel);


            JButton restartBtn = new JButton("Restart");
            restartBtn.setSize(120, 50);
            restartBtn.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2, (this.getHeight() + chessBoardPanel.getHeight()) / 2);
            add(restartBtn);
            restartBtn.addActionListener(e -> {
                System.out.println("click restart Btn");

                if(GameFrame.controller.canContinue()) {
                    int userOption = JOptionPane.showConfirmDialog(null, "Are you sure?There is no winner yet!", "WARNING", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(userOption==JOptionPane.OK_OPTION)
                    {
                        GameFrame gameFrame =new GameFrame();
                        this.setVisible(false);
                        add(gameFrame);
                    }
                }
                else
                {
                    GameFrame gameFrame =new GameFrame();
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



            this.setVisible(true);
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        }
}