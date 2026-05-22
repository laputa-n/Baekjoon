import java.util.*;

class Solution {
    static int[] dRow = {-1,0,1,0};
    static int[] dCol = {0,1,0,-1};
    static final int INF = 100000001;
    public int solution(int[][] board) {
        int N = board.length;
        
        int[][][] cost = new int[N][N][4];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                Arrays.fill(cost[i][j], INF);
            }
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0,0,-1,0));
        Arrays.fill(cost[0][0], 0);
        
        
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            
            if(cur.row == N-1 && cur.col == N-1){
                return cur.cost;
            }
            
            if(cur.dir != -1 && cur.cost > cost[cur.row][cur.col][cur.dir]) continue;
            
            for(int i = 0; i<4; i++){
                int nextRow = cur.row + dRow[i];
                int nextCol = cur.col + dCol[i];
                
                if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;
                if(board[nextRow][nextCol] == 1) continue;
                
                
                int nextCost;
                
                if(cur.dir == -1 || cur.dir % 2 == i%2){
                    nextCost = cur.cost + 100;
                } else {
                    nextCost = cur.cost + 600;
                }
                
                if(nextCost < cost[nextRow][nextCol][i]){
                    cost[nextRow][nextCol][i] = nextCost;
                    pq.add(new Node(nextRow, nextCol, i, nextCost));
                }
            }
        }
        
        return -1;
    }
    static class Node implements Comparable<Node> {
        int row, col, dir, cost;
        
        public Node(int r, int c, int d, int co){
            this.row = r;
            this.col = c;
            this.dir = d;
            this.cost = co;
        }
        
        @Override
        public int compareTo(Node o){
            return Integer.compare(this.cost, o.cost);
        }
    }
}