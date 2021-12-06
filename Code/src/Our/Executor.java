/*package Our;

import model.ChessPiece;

class Executor
{
    private int[][] data= new int[8][8];
    public void setChessBaord(int [][]data)
    {
        this.data=data;
    }
    public boolean canPut(int chess, int PositionX, int PositionY, int moveX, int moveY, int edible)
    {
        if (PositionX+moveX>7||PositionX+moveX<0||PositionY+moveY>7||PositionY+moveY<0){
            return false;//detect the edge
        }
        if(data[PositionX+moveX][PositionY+moveY]==0){
            return false;//detect empty spaces
        }
        else if (data[PositionX+moveX][PositionY+moveY] == -chess){
            return canPut(chess,PositionX+moveX,PositionY+moveY,moveX,moveY,edible+1);
        }
        else if(data[PositionX+moveX][PositionY+moveY] == chess && edible!=0){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean canPut(int chess,int PositionX, int PositionY)//这里可以下棋,多态
    {
        int[] directionX = new int[]{0, 0, 1, 1, -1, -1, 1, -1};
        int[] directionY = new int[]{1, -1, 1, -1, -1, 1, 0, 0};
        boolean ans = false;

        for (int i = 0; i < 8; i++) {
            if (canPut(chess, PositionX, PositionY, directionX[i], directionY[i], 0)) {
                ans=true;
            }
        }
        return ans;
    }

    public void Put(int chess, int PositionX, int PositionY, int moveX, int moveY){
        if (!(PositionX+moveX>7&&//detect the edge
                PositionX+moveX<0&&
                PositionY+moveY>7&&
                PositionY+moveY<0)&&
                data[PositionX+moveX][PositionY+moveY]==-chess)
        {
            data[PositionX+moveX][PositionY+moveY]=chess;
            Put(chess,PositionX+moveX,PositionY+moveY,moveX,moveY);
        }
    }


    public void Put(int chess,int PositionX, int PositionY)
    {
        int[] directionX = new int[]{0, 0, 1, 1, -1, -1, 1, -1};
        int[] directionY = new int[]{1, -1, 1, -1, -1, 1, 0, 0};
        for(int i=0;i<8;i++)
        {
            if(canPut(chess, PositionX, PositionY, directionX[i], directionY[i], 0))
            {
                Put(chess, PositionX, PositionY, directionX[i], directionY[i]);
            }
        }
    }

    public void setBoard(int[][]board)
    {
        for (int PositionX = 0; PositionX < 8; PositionX++)
        {
            for (int PositionY = 0; PositionY < 8; PositionY++)
            {
                this.data[PositionY][PositionX] = board[PositionY][PositionX];
            }
        }
    }
    public int[][] getBoard()
    {
        return this.data;
    }

    public boolean canContinue()//游戏可以继续
    {
        boolean ans=false;
        for(int PositionX=0;PositionX<8;PositionX++)
            for(int PositionY=0;PositionY<8;PositionY++)
            {
                if(canPut(PositionX,PositionY,data[PositionY][PositionX]))ans=true;
            }
        return ans;
    }

    public int findWinner() {
        if (canContinue()) {
            return 404;
        }
        int blackCnt = 0, whiteCnt = 0;
        for (int PositionX = 0; PositionX < 8; PositionX++) {
            for (int PositionY = 0; PositionY < 8; PositionY++) {
                if (data[PositionY][PositionX] == -1) {
                    blackCnt++;
                } else if (data[PositionY][PositionX] == 1) {
                    whiteCnt++;
                }
            }
        }

        if(blackCnt>whiteCnt){
            return -1;//black win
        }else if(blackCnt<whiteCnt){
            return 1;//white winn
        }else {
            return 0;//drawn
        }
    }
    //If Mr.Rbt is reading this:
    //Du, Verräter der Arbeiterklasse, Verpfeif dich!
}
*/
