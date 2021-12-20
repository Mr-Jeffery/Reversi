package view;

import model.ChessPiece;

import javax.swing.*;
import java.awt.*;

public class StatusPanel extends JPanel {
    private JLabel playerLabel;
    private JLabel scoreLabel;

    public StatusPanel(int width, int height,int bscore,int wscore) {
        this.setSize(500, height);
        this.setLayout(null);
        this.setBackground(Color.LIGHT_GRAY);
        this.setOpaque(false);



        this.playerLabel = new JLabel();
        this.playerLabel.setOpaque(false);
        this.playerLabel.setLocation(30, 0);
        this.playerLabel.setSize(720, height);
        this.playerLabel.setForeground(Color.WHITE);
        this.playerLabel.setFont(new Font("Calibri", Font.BOLD, 30));
        this.setPlayerText(ChessPiece.BLACK.name());
        add(this.playerLabel);

        this.scoreLabel = new JLabel();
        this.scoreLabel.setOpaque(false);
        this.scoreLabel.setLocation(240, 0);
        this.scoreLabel.setSize((int)720, height);
        this.scoreLabel.setForeground(Color.WHITE);
        this.scoreLabel.setFont(new Font("Calibri", Font.ITALIC, 25));
        this.setScoreText(bscore,wscore);
        add(this.scoreLabel);


    }

    public void setScoreText(int black, int white) {
        this.scoreLabel.setText(String.format("BLACK: %d\tWHITE: %d", black, white));
        this.scoreLabel.setOpaque(false);
    }

    public void setPlayerText(String playerText) {
        this.playerLabel.setText(playerText + "'s turn");
        this.playerLabel.setOpaque(false);
    }

    public void setAIText(String Me)
    {
        this.playerLabel.setText("Mine:"+Me);
        this.playerLabel.setOpaque(false);
    }

}
