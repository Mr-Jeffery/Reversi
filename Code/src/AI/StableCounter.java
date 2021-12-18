package AI;

public class StableCounter {
    private int[][] data= new int[8][8];
    private int[][] count= new int[8][8];

    public int stableCnt(int chess){
        int cnt=0;
        for (int positionX = 0; positionX < 8; positionX++) {
            for (int positionY = 0; positionY < 8; positionY++) {
                if (this.data[positionY][positionX]==chess&&
                        (this.count[positionY][positionX]==2*chess
                        ||isStable(chess,positionX,positionY,0))){
                    cnt++;
                }
            }
        }
        return cnt;
    }


    public void setData(int[][] data){
        this.data=data;
        for (int positionX = 0; positionX < 8; positionX++) {
            for (int positionY = 0; positionY < 8; positionY++) {
               this.count[positionY][positionX]=data[positionY][positionX];
            }
        }
    }

    public boolean isStable(int chess, int positionX, int positionY,int depth){
        if (count[positionX][positionY]==2*chess){
            return true;
        }
        if (depth>5){
            return false;

        } else{
            int[] directionX = new int[]{0, 0, 1, 1, -1, -1, 1, -1};
            int[] directionY = new int[]{1, -1, 1, -1, -1, 1, 0, 0};
            boolean answer = true;
            for (int i = 0; i < 8; i++) {
                answer=isStable(chess, positionX, positionY, directionX[i], directionY[i],depth++)&&answer;
            }
            if (answer){
                count[positionX][positionY] = 2*count[positionX][positionY];
            }
            return answer;
        }
    }
    private boolean isStable(int chess, int positionX, int positionY, int moveX, int moveY,int depth) {
        return !(hasSpace(positionX,positionY,moveX,moveY)&&//has space on one side
                (hasEnemy(chess,positionX,positionY,-moveX,-moveY,depth++)||//has space or enemy on the other side
                        hasSpace(positionX,positionY,-moveX,-moveY)));
    }

    private boolean hasEnemy(int chess, int positionX, int positionY, int moveX, int moveY,int depth) {
        if (positionX+moveX>7||positionX+moveX<0||positionY+moveY>7||positionY+moveY<0){
            return false;//detect the edge
        }else if(data[positionX+moveX][positionY+moveY]==chess&&
                isStable(chess,positionX+moveX,positionY+moveY,depth++)){
                return hasEnemy(chess,positionX+moveX,positionY+moveY,moveX,moveY,depth++);
        }else {
            return true;
        }
    }

    private boolean hasSpace(int positionX, int positionY, int moveX, int moveY) {
        if (positionX+moveX>7||positionX+moveX<0||positionY+moveY>7||positionY+moveY<0){
            return false;//detect the edge
        }else if(data[positionX+moveX][positionY+moveY]==0){
            return true;//detect empty spaces
        }else{
            return hasSpace(positionX+moveX,positionY+moveY,moveX,moveY);
        }
    }
}
