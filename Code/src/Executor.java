class Executor
{
    public int[][] data= new int[8][8];
    public boolean canPut(int chess,int PositionX, int PositionY, int moveX, int moveY,int edible)
    {
        if (PositionX+moveX>7||PositionX+moveX<0||PositionY+moveY>7||PositionY+moveY<0){
            return false;//detect the edge
        }
        if(data[PositionX+moveX][PositionY+moveY]==0){
            return false;//detect empty spaces
        }
        else if (data[PositionX+moveX][PositionY+moveY]==-chess){
            return canPut(chess,PositionX+moveX,PositionY+moveY,moveX,moveY,edible+1);
        }
        else if(data[PositionX+moveX][PositionY+moveY]==chess&&edible!=0){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean canPut(int chess,int PositionX, int PositionY)//这里可以下棋
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

    //If Mr.Rbt is reading this:
    //Du, Verräter der Arbeiterklasse, Verpfeif dich!
}