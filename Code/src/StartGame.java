
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
            if(canSet(PositionX,PositionY,now))
            {
                for (int i = 0; i < 8; i++)
                    if (executor.canPut(now, PositionX, PositionY, directionX[i], directionY[i], 0))
                        executor.Put(now, PositionX, PositionY, directionX[i], directionY[i]);
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
    for(int i=0;i<8;i++)
        for(int j=0;j<8;j++)
        {
            if(board[i][j]==0 && canSet(i,j,board[i][j]))ans=true;
        }
    return ans;
    }

    public boolean canSet(int PositionX,int PositionY,int now)//这里可以下棋
    {
        boolean ans=false;
        for(int i=0;i<8;i++)
        {
            if(executor.canPut(now, PositionX, PositionY, directionX[i], directionY[i], 0))ans=true;
        }
        return ans;
    }

}
