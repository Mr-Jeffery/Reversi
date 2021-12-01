import java.util.ArrayList;

class ComputerPlayer {
    protected int [][] board;
    int chess;
    long knotNum;
    public void play(){
        Node node = new Node();
        Node metaNode = new Node(node,knotNum,true,board,chess);
        metaNode.branch();
        metaNode.search();
    }



    static class Node
    {
        protected int [][]board;
        int chess;
        Executor executor = new Executor();
        private int plausible;
        private long knotNum;
        private boolean M;
        ArrayList<int[]> subNodeCoordinate = new ArrayList<>();
        ArrayList<Node> subNode = new ArrayList<>();
        protected int value;
        int alpha=-114514,beta=114514;
        Node surNode;
        final static int[][] WeightTable = {
                { 15, -5, 10,  5,  5, 10, -5, 15},
                { -5,-10,  1,  1,  1,  1,-10, -5},
                { 10,  1,  3,  2,  2,  3,  1, 10},
                {  5,  1,  2,  1,  1,  2,  1,  5},
                {  5,  1,  2,  1,  1,  2,  1,  5},
                { 10,  1,  3,  2,  2,  3,  1, 10},
                { -5,-10,  1,  1,  1,  1,-10, -5},
                { 15, -5, 10,  5,  5, 10, -5, 15}
        };

        public Node(){}
        public Node(Node surNode,long knotNum,boolean M,int [][]board,int chess)
        {
            this.surNode = surNode;
            this.knotNum = knotNum;
            this.board = board;
            this.M = M;
            this.chess=chess;
            if (M){value=-114514;}else {value=114514;}
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

        public void search() {
            if (plausible==0) {
                if(executor.canContinue()){
                    for (int PositionX = 0; PositionX < 8; PositionX++) {
                        for (int PositionY = 0; PositionY < 8; PositionY++) {
                            value += WeightTable[PositionY][PositionX]*board[PositionY][PositionX];
                        }
                    }
                }else {
                    value=executor.findWinner()*114514;
                }
                if(M){alpha=value;}
                else {beta=value;}
            }else {
                for (Node N : subNode) {
                    N.search();
                    if (M){//find max
                        if(N.value>this.value) {
                            this.value=N.value;
                            this.alpha=N.beta;
                        }
                    } else {//find min
                        if(N.value<this.value) {
                            this.value=N.value;
                            this.beta=N.alpha;
                        }
                    }
                    if(this.alpha>surNode.beta||this.beta<surNode.alpha){
                        break;
                    }
                }

            }
        }


        public void branch() {
            if (plausible<knotNum){
                Executor executor = new Executor();
                for (int i=0;i<this.plausible;i++) {
                    executor.setBoard(board);
                    executor.Put(chess, subNodeCoordinate.get(i)[0], subNodeCoordinate.get(i)[1]);
                    Node subNode = new Node(this, knotNum/plausible, !M, executor.getBoard(),-chess);
                    this.subNode.add(subNode);
                }
            }

        }
    }
}
