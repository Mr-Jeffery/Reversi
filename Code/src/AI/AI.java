package AI;

import java.util.ArrayList;
import java.util.List;

public class AI {

    Node metaNode ,oNode;
    public void setLayerTotal(int layerTotal){
        Node.setLayerTotal(layerTotal);
    }

    public int[] play(int[][] board,int chess){
        boolean M;
        if (chess==-1){M=false;}else {M=true;}
        oNode = new Node();
        oNode.moves=new ArrayList<>();
        oNode.moves.add(oNode);
        Node.layerTotal=8;
        metaNode = new Node();
        metaNode.search(oNode,0,M,board,new int[]{},chess);
        return metaNode.getMove();
    }

    static class Node {
        protected int chess;
        List<Node> moves = new ArrayList<>();
        protected int[][] board;
        int[] coordinates;
        Runner runner = new Runner();
        public int layer;
        private static int layerTotal ;
        private boolean M;
        ArrayList<Node> subNode=new ArrayList<>();
        protected int value;
        Node surNode;
        final static int[][] WeightTable = {
                {15, -5, 10, 5, 5, 10, -5, 15},
                {-5, -10, 1, 1, 1, 1, -10, -5},
                {10, 1, 3, 2, 2, 3, 1, 10},
                {5, 1, 2, 1, 1, 2, 1, 5},
                {5, 1, 2, 1, 1, 2, 1, 5},
                {10, 1, 3, 2, 2, 3, 1, 10},
                {-5, -10, 1, 1, 1, 1, -10, -5},
                {15, -5, 10, 5, 5, 10, -5, 15}
        };

        public void search(Node surNode, int layer, boolean M, int[][] board, int[] coordinates, int chess) {
            this.moves=new ArrayList<>(surNode.moves);
            this.moves.add(this);
            this.surNode = surNode;//last board
            this.coordinates = coordinates;//last step
            this.layer = layer;//search depth
            this.board = board;//current board
            this.M = M;
            this.chess = chess;//current color
            if (M) {
                value = -114514;
            } else {
                value = 114514;
            }//Max,Min
            runner.setBoard(board);
            if (layer < layerTotal&&runner.canContinue()) {//search plausible next step
                for (int PositionX = 0; PositionX < 8; PositionX++) {
                    for (int PositionY = 0; PositionY < 8; PositionY++) {
                        runner.setBoard(board);
                        if(runner.getBoard()[PositionY][PositionX]!=0){
                            continue;
                        }
                        if (runner.canPut(this.chess, PositionX, PositionY)) {
                            runner.put(this.chess,PositionX,PositionY);
                            Node sNode = new Node();
                            sNode.search(this, layer + 1, !M, runner.getBoard(), new int[]{PositionX, PositionY}, -chess);
                            if (M){//find max
                                if(sNode.value>this.value) {
                                    this.moves=sNode.moves;
                                    this.value=sNode.value;
                                }
                            } else {//find min
                                if(sNode.value<this.value) {
                                    this.moves=sNode.moves;
                                    this.value=sNode.value;
                                }
                            }
                            if((M&&this.value>=surNode.value)
                                    ||(!M&&this.value<=surNode.value))
                            {
                                System.out.println("break");
                                break;
                            }
                        }
                    }
                }
            }else if (this.layer == layerTotal||subNode.isEmpty()) {//has no next step
                if(runner.canContinue()){//game has not ended yet
                    value=0;
                    for (int PositionX = 0; PositionX < 8; PositionX++) {
                        for (int PositionY = 0; PositionY < 8; PositionY++) {
                            value += WeightTable[PositionY][PositionX]*board[PositionY][PositionX];//权值表计算
                        }
                    }
                    StableCounter stableCounter = new StableCounter();//稳定子计算
                    stableCounter.setData(this.board);
                    value = value
                            + stableCounter.stableCnt(-1)*5
                            + stableCounter.stableCnt(1)*5;

                }else {
                    value= runner.findWinner()*114514;//game ended
                }
            }
        }

        public int[] getMove(){
            return this.moves.get(2).coordinates;
        }

        public static void setLayerTotal(int layerTotal){
            Node.layerTotal =layerTotal;
        }

    }
}
