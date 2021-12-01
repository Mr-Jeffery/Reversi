import java.util.ArrayList;

public class AI
{


    static class Node
    {
        protected int [][]board;
        int chess;
        Executor executor = new Executor();
        private int plausible;
        final private long knotNum;
        final private boolean M;
        ArrayList<int[]> subNodeCoordinate = new ArrayList<>();
        ArrayList<Node> subNode = new ArrayList<>();
        int value;
        int alpha,beta;
        Node surNode;
        final int[][] WeightTable = {
                { 15, -5, 10, 5, 5, 10, -5, 15},
                { -5,-10,  1, 1, 1,  1,-10, -5},
                { 10,  1,  3, 2, 2,  3,  1, 10},
                {  5,  1,  2, 1, 1,  2,  1,  5},
                {  5,  1,  2, 1, 1,  2,  1,  5},
                { 10,  1,  3, 2, 2,  3,  1, 10},
                { -5,-10,  1, 1, 1,  1,-10, -5},
                { 15, -5, 10, 5, 5, 10, -5, 15}
        };


        public Node(Node surNode,long knotNum,boolean M,int [][]board,int chess)
        {
            this.surNode = surNode;
            this.knotNum = knotNum;
            this.board = board;
            this.M = M;
            this.chess=chess;

            for (int PositionX = 0; PositionX < 8; PositionX++) {
                for (int PositionY = 0; PositionY < 8; PositionY++) {
                    executor.setBoard(board);
                    if (executor.canPut(chess, PositionX, PositionY)) {
                        this.subNodeCoordinate.add(new int[]{PositionX, PositionY});
                        plausible++;
                    }
                }
            }
        }

        public int Search() {
            if (plausible==0) {
                if(executor.canContinue()){
                    for (int PositionX = 0; PositionX < 8; PositionX++) {
                        for (int PositionY = 0; PositionY < 8; PositionY++) {
                            value += WeightTable[PositionX][PositionY];
                        }
                    }
                }
            }else {
                for (Node N : subNode) {
                    N.Search();
                    if (M){//find max
                        value=-114514;
                        if(N.value>this.value) {
                            this.value=N.value;
                            this.alpha=N.value;
                        }
                        if(this.alpha<surNode.beta||this.beta>surNode.alpha){
                            break;
                        }
                    }
                    else {//find min
                        value=114514;
                        if(N.value<this.value) {
                            this.value=N.value;
                            this.beta=N.value;
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
                    executor.setBoard(board);
                    executor.Put(chess, subNodeCoordinate.get(i)[0], subNodeCoordinate.get(i)[1]);
                    Node subNode = new Node(this, knotNum/plausible, !M, executor.getBoard(),-chess);
                    this.subNode.add(subNode);
                }
            }

        }
    }
}
