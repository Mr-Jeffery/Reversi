class Chessboard{
    public int[][] data= new int[8][8];
    public int chess;
    public boolean canPut(int PositionX, int PositionY, int moveX, int moveY,int edible){
        if (PositionX+moveX>7||PositionX+moveX<0||PositionY+moveY>7||PositionY+moveY<0){
            return false;//detect the edge
        }
        if(data[PositionY+moveY][PositionX+moveX]==0){
            return false;//detect empty spaces
        }
        else if (data[PositionY+moveY][PositionX+moveX]==-chess){
            return canPut(PositionX+moveX,PositionY+moveY,moveX,moveY,edible+1);
        }
        else if(data[PositionY+moveY][PositionX+moveX]==chess&&edible!=0){
            return true;
        }
        else {
            return false;
        }
    }
    public void Put(int PositionX, int PositionY, int moveX, int moveY){
        if (!(PositionX+moveX>7&&//detect the edge
                PositionX+moveX<0&&
                PositionY+moveY>7&&
                PositionY+moveY<0)
                &&data[PositionY+moveY][PositionX+moveX]==-chess)
        {
            data[PositionY+moveY][PositionX+moveX]=chess;
            Put(PositionX+moveX,PositionY+moveY,moveX,moveY);
        }
    }
}