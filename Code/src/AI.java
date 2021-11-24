import java.util.ArrayList;

public class AI
{


    class Node
    {
        protected int [][]board;
        private long knotNum;
        private boolean M;
        ArrayList<Node> subNode = new ArrayList<>();
        int value;
        int alpha,beta;
        Node surNode;
        final int[][] WeightTable = {
                {100, -5, 10, 5, 5, 10, -5,100},
                { -5,-45,  1, 1, 1,  1,-45, -5},
                { 10,  1,  3, 2, 2,  3,  1, 10},
                {  5,  1,  2, 1, 1,  2,  1,  5},
                {  5,  1,  2, 1, 1,  2,  1,  5},
                { 10,  1,  3, 2, 2,  3,  1, 10},
                { -5,-45,  1, 1, 1,  1,-45, -5},
                {100, -5, 10, 5, 5, 10, -5,100}
        };

        public Node(Node surNode,long knotNum,boolean M,int [][]board,int chess)
        {
            Executor executor = new Executor();
            this.surNode=surNode;
            this.knotNum=knotNum;
            this.board=board;
            executor.data=board;
            this.M=M;
            for(int i=0;i<8;i++)
                for(int j=0;j<8;j++)
                {
                    if (executor.canPut(chess,i,j)){

                    }
                }
        }
    }
}
