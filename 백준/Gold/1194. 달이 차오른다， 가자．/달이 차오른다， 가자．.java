import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static int N,M;
    static char[][] board;
    static Node start;
    static int bfs(){
        Queue<Node> q = new LinkedList<>();
        boolean[][][] visited = new boolean[N][M][1<<6];
        q.offer(start);
        visited[start.row][start.col][0] = true;
        while(!q.isEmpty()){
            Node cur = q.poll();
            if(board[cur.row][cur.col] == '1'){
                return cur.dist;
            }
            for(int i = 0; i<4; i++){
                int nextRow = cur.row + dx[i];
                int nextCol = cur.col + dy[i];
                if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) continue;
                if(visited[nextRow][nextCol][cur.key] || board[nextRow][nextCol] == '#') continue;

                if(board[nextRow][nextCol] >= 'a' && board[nextRow][nextCol] <= 'f'){
                    int nextKey = 1 << (board[nextRow][nextCol] - 'a');
                    nextKey = nextKey|cur.key;
                    visited[nextRow][nextCol][nextKey] = true;
                    q.offer(new Node(nextRow,nextCol,cur.dist+1,nextKey));
                } else if (board[nextRow][nextCol]>='A' && board[nextRow][nextCol]<='F'){
                    if((cur.key& (1<<(board[nextRow][nextCol]-'A'))) == 0) continue;
                    visited[nextRow][nextCol][cur.key] = true;
                    q.offer(new Node(nextRow,nextCol,cur.dist+1,cur.key));
                } else {
                    visited[nextRow][nextCol][cur.key] = true;
                    q.offer(new Node(nextRow,nextCol,cur.dist+1,cur.key));
                }
            }
        }

        return -1;

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        for(int i = 0; i < N; i++){
            String line = br.readLine();
            for(int j = 0; j < M; j++){
                board[i][j] = line.charAt(j);
                if(board[i][j] == '0')
                    start = new Node(i,j,0,0);
            }
        }
        bw.write(String.valueOf(bfs()));
        bw.flush();
        bw.close();
        br.close();
    }
    static class Node {
        int row,col,dist,key;
        Node(int row, int col, int dist, int key) {
            this.row = row;
            this.col = col;
            this.dist = dist;
            this.key = key;
        }
    }
}
