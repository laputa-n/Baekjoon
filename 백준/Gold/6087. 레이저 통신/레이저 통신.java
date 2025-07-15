
import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx ={-1,0,1,0};
    static int[] dy = {0,-1,0,1};
    static Node[] target;
    static int H,W;
    static char[][] board;
    static int bfs(Node n){
        int min = Integer.MAX_VALUE;
        Node goal = target[1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[][][] visited = new int[4][H][W];
        for(int i = 0; i<4; i++){
            for(int j = 0; j<H; j++){
                Arrays.fill(visited[i][j], Integer.MAX_VALUE);
            }
        }
        pq.offer(n);
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(cur.row == goal.row && cur.col == goal.col){
                min = Math.min(min, cur.mirrors);
                continue;
            }
            for(int i = 0; i<4; i++){
                int nextRow = cur.row + dx[i];
                int nextCol = cur.col + dy[i];
                int nextMirrors = (cur.dir == i) ? cur.mirrors: cur.mirrors+1;
                if(nextRow < 0 || nextRow >= H || nextCol < 0 || nextCol >= W) continue;
                if(board[nextRow][nextCol] == '*' || Math.abs(cur.dir - i) == 2) continue;

                if(visited[i][nextRow][nextCol] > nextMirrors){
                    pq.offer(new Node(nextRow,nextCol,i,nextMirrors));
                    visited[i][nextRow][nextCol] = nextMirrors;
                }
            }
        }
        return min;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        board = new char[H][W];
        target = new Node[2];
        for(int i = 0, idx = 0; i < H; i++){
            board[i] = br.readLine().toCharArray();
            for(int j = 0; j < W; j++){
                if(board[i][j] == 'C'){
                    target[idx++] = new Node(i,j,-5,-1);
                }
            }
        }
        bw.write(String.valueOf(bfs(target[0])));
        bw.flush();
        bw.close();
        br.close();
    }
    public static class Node implements Comparable<Node> {
        int row,col,dir,mirrors;

        Node(int row, int col, int dir, int mirrors) {
            this.row = row;
            this.col = col;
            this.dir = dir;
            this.mirrors = mirrors;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.mirrors, o.mirrors);
        }
    }
}



