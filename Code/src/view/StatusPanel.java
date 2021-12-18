package view;

import model.ChessPiece;

import javax.swing.*;
import java.awt.*;

public class StatusPanel extends JPanel {
    private JLabel playerLabel;
    private JLabel scoreLabel;

    public StatusPanel(int width, int height,int bscore,int wscore) {
        this.setSize(width, height);
        this.setLayout(null);


        this.playerLabel = new JLabel();
        this.playerLabel.setOpaque(false);
        this.playerLabel.setLocation(0, 0);
        this.playerLabel.setSize((int) (width * 0.4), height);
        this.playerLabel.setFont(new Font("Calibri", Font.BOLD, 30));
        this.setPlayerText(ChessPiece.BLACK.name());
        add(this.playerLabel);

        this.scoreLabel = new JLabel();
        this.scoreLabel.setOpaque(false);
        this.scoreLabel.setLocation(210, 0);
        this.scoreLabel.setSize((int) (width * 0.5), height);
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

}
