package AI;

public class Runner
{
    private final int[][] data= new int[8][8];

    public boolean canPut(int chess, int positionX, int positionY, int moveX, int moveY, int edible) {
        if (positionX+moveX>7||positionX+moveX<0||positionY+moveY>7||positionY+moveY<0){
            return false;//detect the edge
        }
        if(data[positionX+moveX][positionY+moveY]==0){
            return false;//detect empty spaces
        }
        else if (data[positionX+moveX][positionY+moveY] == -chess){
            return canPut(chess,positionX+moveX,positionY+moveY,moveX,moveY,edible+1);
        }
        else return data[positionX + moveX][positionY + moveY] == chess && edible != 0;
    }
    public boolean canPut(int chess,int positionX, int positionY){//这里可以下棋,多态
        int[] directionX = new int[]{0, 0, 1, 1, -1, -1, 1, -1};
        int[] directionY = new int[]{1, -1, 1, -1, -1, 1, 0, 0};
        boolean ans = false;

        if(data[positionX][positionY]!=0)
        {
            return ans;
        }
        else
        {
            for (int i = 0; i < 8; i++) {
                if (canPut(chess, positionX, positionY, directionX[i], directionY[i], 0)) {
                    ans=true;
                }
            }
        }

        return ans;
    }


    public void put(int chess, int positionX, int positionY, int moveX, int moveY){
        if (!(positionX+moveX>7&&//detect the edge
                positionX+moveX<0&&
                positionY+moveY>7&&
                positionY+moveY<0)&&
                data[positionX+moveX][positionY+moveY]==-chess)
        {
            data[positionX+moveX][positionY+moveY]=chess;
            put(chess,positionX+moveX,positionY+moveY,moveX,moveY);
        }
    }
    public void put(int chess, int positionX, int positionY) {
        data[positionX][positionY]=chess;
        int[] directionX = new int[]{0, 0, 1, 1, -1, -1, 1, -1};
        int[] directionY = new int[]{1, -1, 1, -1, -1, 1, 0, 0};
        for(int i=0;i<8;i++)
        {
            if(canPut(chess, positionX, positionY, directionX[i], directionY[i], 0))
            {
                put(chess, positionX, positionY, directionX[i], directionY[i]);
            }
        }
    }

    
    public void setBoard(int[][]board) {
        for (int positionX = 0; positionX < 8; positionX++) {
            for (int positionY = 0; positionY < 8; positionY++) {
                this.data[positionY][positionX] = board[positionY][positionX];
            }
        }
    }
    public int[][] getBoard() {
        return this.data;
    }

    
    public boolean canContinue(){//游戏可以继续
        for(int positionX=0;positionX<8;positionX++)
            for(int positionY=0;positionY<8;positionY++) {
                if(canPut(-1,positionX,positionY)||canPut(1,positionX,positionY)){
                    return true;
                }
            }
        return false;
    }

    public int findWinner() {
        if (canContinue()) {
            return 0;
        }
        int blackCnt = 0, whiteCnt = 0;

        for (int positionX = 0; positionX < 8; positionX++) {
            for (int positionY = 0; positionY < 8; positionY++) {
                if (data[positionY][positionX] == -1) {
                    blackCnt++;
                }else if (data[positionY][positionX] == 1) {
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

