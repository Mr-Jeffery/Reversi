class Executor {
    public int[][] data= new int[8][8];
    public boolean canPut(int chess,int PositionX, int PositionY, int moveX, int moveY,int edible){
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
    public void Put(int chess,int PositionX, int PositionY, int moveX, int moveY){
        if (!(PositionX+moveX>7&&//detect the edge
                PositionX+moveX<0&&
                PositionY+moveY>7&&
                PositionY+moveY<0)&&
                data[PositionX+moveX][PositionY+moveY]==-chess)
        {
            data[PositionX+moveX][PositionY+moveY]=chess;
            Put(chess,PositionX+moveX,PositionY+moveY,moveX,moveY);
        }
    }//If Mr.Rbt is reading this:
    //Du, Verräter der Arbeiterklasse, Verpfeif dich!
}