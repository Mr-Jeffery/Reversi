
public class StartGame {
    public int[][] data= new int[8][8];
    boolean isstart = true;
    int[][] board= new int[8][8];
    int[] directionX = new int[]{0, 0, 1, 1, -1, -1, 1, -1};
    int[] directionY = new int[]{1, -1, 1, -1, -1, 1, 0, 0};
    int now=1;
    Executor executor =new Executor();

    public void  play()//
    {
        while(isstart)
        {
            //在这里点击按钮下棋子
            //当点击的范围在  之间，则当前位置为该位置positionX，positionY；
            int PositionX=1;
            int PositionY=1;
            if(executor.canPut(now,PositionX,PositionY))
            {
                executor.Put(now, PositionX, PositionY);
            }
            else
                //显示："这里不能下棋！！！"
                now=-now;
            if(!canContinue(board))
            {
                isstart=false;
                //游戏结束
                //计算结果并且打印呈现在面板上面
            }
        }
    }


    public boolean canContinue(int [][] board)//游戏可以继续
    {
    boolean ans=false;
    for(int PositionX=0;PositionX<8;PositionX++)
        for(int PositionY=0;PositionY<8;PositionY++)
        {
            if(board[PositionX][PositionY]==0 && executor.canPut(PositionX,PositionY,board[PositionX][PositionY]))ans=true;
        }
    return ans;
    }



}
