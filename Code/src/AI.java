import java.util.ArrayList;

public class AI
{


    static class Node
    {
        protected int [][]board;
        int chess;
        private int plausible;
        final private long knotNum;
        final private boolean M;
        ArrayList<int[]> subNodeCoordinate = new ArrayList<>();
        ArrayList<Node> subNode = new ArrayList<>();
        int value;
        int alpha,beta;
        Node surNode;
        final int[][] WeightTable = {
                { 50, -5, 10, 5, 5, 10, -5, 50},
                { -5,-45,  1, 1, 1,  1,-45, -5},
                { 10,  1,  3, 2, 2,  3,  1, 10},
                {  5,  1,  2, 1, 1,  2,  1,  5},
                {  5,  1,  2, 1, 1,  2,  1,  5},
                { 10,  1,  3, 2, 2,  3,  1, 10},
                { -5,-45,  1, 1, 1,  1,-45, -5},
                { 50, -5, 10, 5, 5, 10, -5,50}
        };


        public Node(Node surNode,long knotNum,boolean M,int [][]board,int chess)
        {
            this.surNode = surNode;
            this.knotNum = knotNum;
            this.board = board;
            this.M = M;
            this.chess=chess;
            Executor executor = new Executor();
            for (int PositionX = 0; PositionX < 8; PositionX++) {
                for (int PositionY = 0; PositionY < 8; PositionY++) {
                    executor.setData(board);
                    if (executor.canPut(chess, PositionX, PositionY)) {
                        this.subNodeCoordinate.add(new int[]{PositionX, PositionY});
                        plausible++;
                    }
                }
            }
        }

        public int Search() {
            if (plausible!=0) {
                for (int PositionX = 0; PositionX < 8; PositionX++) {
                    for (int PositionY = 0; PositionY < 8; PositionY++) {
                        value+=WeightTable[PositionX][PositionY];
                    }
                }
            }else {
                for (Node N : subNode) {
                    N.Search();
                    if (M){//find max
                        value=-114514;
                        if(N.value>this.value) {
                            this.value=N.value;
                        }
                    }
                    else {//find min
                        value=114514;
                        if(N.value<this.value) {
                            this.value=N.value;
                        }
                    }
                }

            }
            return value;
        }


        public void Branch()
        {
            if (plausible<knotNum){
                Executor executor = new Executor();
                for (int i=0;i<this.plausible;i++)
                {
                    executor.setData(board);
                    executor.Put(chess, subNodeCoordinate.get(i)[0], subNodeCoordinate.get(i)[1]);
                    Node subNode = new Node(this, knotNum/plausible, !M, executor.getBoard(),-chess);
                    this.subNode.add(subNode);
                }
            }

        }
    }
}
