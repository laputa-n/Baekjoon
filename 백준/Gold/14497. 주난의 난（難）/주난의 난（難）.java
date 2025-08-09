import org.w3c.dom.Node;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[] dRow = {-1,0,1,0};
    static int[] dCol = {0,-1,0,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int x1 = Integer.parseInt(st.nextToken());
        int y1 = Integer.parseInt(st.nextToken());
        int x2 = Integer.parseInt(st.nextToken());
        int y2 = Integer.parseInt(st.nextToken());
        int[][] matrix = new int[N+1][M+1];
        for(int i = 1; i <= N; i++){
            String[] s = br.readLine().split("");
            for(int j = 1; j <= M; j++){
                String temp = s[j-1];
                if(temp.equals("#")) matrix[i][j] = 3; // 도착지 # -> 3
                else if(temp.equals("*")) matrix[i][j] = 2; // 출발지 * -> 2
                else matrix[i][j] =Integer.parseInt(temp);
            }
        }
        int[][] dist = new int[N+1][M+1];
        for(int i = 1; i <= N; i++){
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(x1,y1,1));
        dist[x1][y1] = 1;
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(cur.row == x2 && cur.col == y2){
                bw.write(String.valueOf(cur.cnt));
                pq.clear();
                break;
            }
            for(int i = 0; i<4; i++){
                int nextRow = cur.row + dRow[i];
                int nextCol = cur.col + dCol[i];
                if(nextRow < 1 || nextRow > N || nextCol < 1 || nextCol > M) continue;
                if(matrix[nextRow][nextCol] == 1){
                    if(dist[nextRow][nextCol]>cur.cnt+1){
                        dist[nextRow][nextCol] = cur.cnt+1;
                        pq.offer(new Node(nextRow,nextCol,cur.cnt+1));
                    }
                } else{
                    if(dist[nextRow][nextCol]>cur.cnt){
                        dist[nextRow][nextCol] = cur.cnt;
                        pq.offer(new Node(nextRow,nextCol,cur.cnt));
                    }
                }
            }

        }
        bw.flush();
        bw.close();
        br.close();
    }
    static class Node implements Comparable<Node>{
        int row, col , cnt;
        Node(int row, int col, int cnt){
            this.row = row;
            this.col = col;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o) {
            return cnt - o.cnt;
        }

    }
}
