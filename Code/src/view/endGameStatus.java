package view;

import model.ChessPiece;

import javax.swing.*;
import java.awt.*;

public class endGameStatus extends JPanel{
    private JLabel tipsLabel;

    public endGameStatus(int wid,int hei)
    {
        this.setSize(wid, hei);
        this.setLayout(null);
        this.setVisible(true);

        this.tipsLabel = new JLabel();
        this.tipsLabel.setLocation((int)(wid * 0.7), 10);
        this.tipsLabel.setSize((int) (wid * 0.4), hei);
        this.tipsLabel.setFont(new Font("Calibri", Font.BOLD, 20));
        this.setTipsText(ChessPiece.BLACK.name());
        add(tipsLabel);//将这些东东加进去
    }
    public void setTipsText(String s)
    {
        this.tipsLabel.setText(String.format("THE WINNER IS:"+s));
    }
}
