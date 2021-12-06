package components;

import model.*;
import view.GameFrame;

import java.awt.*;

public class ChessGridComponent extends BasicComponent {
    public static int chessSize;
    public static int gridSize;
    public static Color gridColor = new Color(211, 170, 130);

    private ChessPiece chessPiece;
    private int row;
    private int col;

    public ChessGridComponent(int row, int col) {
        this.setSize(gridSize, gridSize);

        this.row = row;
        this.col = col;
    }

    @Override
    public void onMouseClicked() {
        System.out.printf("%s clicked (%d, %d)\n", GameFrame.controller.getCurrentPlayer(), row, col);
        //找到接口了哈哈哈哈哈哈哈哈
        System.out.println(GameFrame.controller.canClick(row, col));
        if (GameFrame.controller.canClick(row, col)) {//如果该点为空，则可以下棋，重新绘制repaint；
                this.chessPiece = GameFrame.controller.getCurrentPlayer();

                int color=0;
                if(this.chessPiece.getColor()==Color.BLACK)color=1;
                else if(this.chessPiece.getColor()==Color.WHITE)color=-1;
                GameFrame.controller.Putting(color,row,col);

                GameFrame.controller.swapPlayer();
            repaint();
        }
        else
        {

        }
    }


    public ChessPiece getChessPiece() {
        return chessPiece;
    }

    public void setChessPiece(ChessPiece chessPiece) {
        this.chessPiece = chessPiece;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void drawPiece(Graphics g) {
        g.setColor(gridColor);
        g.fillRect(1, 1, this.getWidth() - 2, this.getHeight() - 2);
        if (this.chessPiece != null) {
            g.setColor(chessPiece.getColor());
            g.fillOval((gridSize - chessSize) / 2, (gridSize - chessSize) / 2, chessSize, chessSize);
        }
    }


    @Override
    public void paintComponent(Graphics g) {
        super.printComponents(g);
        drawPiece(g);
    }


}
