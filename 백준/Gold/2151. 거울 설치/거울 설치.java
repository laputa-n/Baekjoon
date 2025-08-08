import java.io.*;
import java.util.PriorityQueue;

public class Main {
    static int[] dRow = {-1,0,1,0};
    static int[] dCol = {0,-1,0,1};
    static int N,startRow,startCol,endRow,endCol;
    static char[][] map;
    static boolean[][][] visited;
    static int totalCnt;
    static void Dijkstra(int startRow, int startCol){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int i = 0; i<4; i++){
            int nextRow = startRow + dRow[i];
            int nextCol = startCol + dCol[i];
            if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N || map[nextRow][nextCol] == '*') continue;
            pq.add(new Node(startRow,startCol,i,0));
        }
        while(!pq.isEmpty()){
            Node node = pq.poll();
            int curRow = node.row;
            int curCol = node.col;
            int curCnt = node.cnt;
            int curDir = node.dir;

            visited[curRow][curCol][curDir] = true;
            if(curRow == endRow && curCol == endCol){
                totalCnt = curCnt;
                return;
            }

            int nRow = curRow + dRow[curDir];
            int nCol = curCol + dCol[curDir];

            if(nRow < 0 || nRow >= N || nCol < 0 || nCol >= N || map[nRow][nCol] == '*') continue;
            if(map[nRow][nCol] == '!'){
                int nDir = (curDir + 1)%4;
                if(!visited[nRow][nCol][nDir]){
                    pq.add(new Node(nRow,nCol, nDir, curCnt+1));
                }
                nDir = (curDir+3)%4;
                if(!visited[nRow][nCol][nDir]){
                    pq.add(new Node(nRow,nCol, nDir, curCnt+1));
                }
            }
            if(!visited[nRow][nCol][curDir]){
                pq.add(new Node(nRow,nCol, curDir, curCnt));
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N][N][4];
        int cnt = 0;
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for(int j = 0; j < N; j++){
                if(map[i][j] == '#'){
                    if(cnt == 0){
                        startRow = i;
                        startCol = j;
                        cnt++;
                    } else {
                        endRow = i;
                        endCol = j;
                    }
                }
            }
        }
        Dijkstra(startRow,startCol);
        bw.write(String.valueOf(totalCnt));
        bw.flush();
        bw.close();
        br.close();
    }
    static class Node implements Comparable<Node>{
        int row,col,dir,cnt;
        Node(int row, int col, int dir, int cnt){
            this.row = row;
            this.col = col;
            this.dir = dir;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o){
            return Integer.compare(this.cnt,o.cnt);
        }
    }
}
