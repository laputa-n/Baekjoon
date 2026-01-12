import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[] dRow = {-1,0,1,0};
    static int[] dCol = {0,-1,0,1};
    static int N,M;
    static char[][] map;
    static int[][] sideGarbageCount, passByGarbageCount;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        sideGarbageCount = new int[N][M];
        passByGarbageCount = new int[N][M];
        for(int i = 0; i < N; i++) {
            Arrays.fill(sideGarbageCount[i], Integer.MAX_VALUE);
            Arrays.fill(passByGarbageCount[i], Integer.MAX_VALUE);
        }
        int startRow = 0, startCol = 0;
        int endRow = 0, endCol = 0;
        for(int i = 0; i < N; i++){
            String line = br.readLine();
            for(int j = 0; j < M; j++){
                map[i][j] = line.charAt(j);
                if(map[i][j] == 'F'){
                    endRow = i;
                    endCol = j;
                }
                if(map[i][j] == 'S'){
                    startRow = i;
                    startCol = j;
                }
            }
        }
        sideGarbageCount[startRow][startCol] = 0;
        passByGarbageCount[startRow][startCol] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(startRow, startCol,0,0,0));
        while(!pq.isEmpty()){
            Node node = pq.poll();

            if(node.garbageCount > passByGarbageCount[node.row][node.col]) continue;
            if(node.garbageCount == passByGarbageCount[node.row][node.col] &&
            node.garbageSideCount > sideGarbageCount[node.row][node.col]) continue;

            if(node.row == endRow && node.col == endCol){
                bw.write(String.valueOf(node.garbageCount) + " " + String.valueOf(node.garbageSideCount));
                break;
            }
            for(int i = 0; i < 4; i++){
                int nextRow = node.row + dRow[i];
                int nextCol = node.col + dCol[i];
                if(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M){
                    int newG = node.garbageCount + (map[nextRow][nextCol] == 'g' ? 1 : 0);
                    int newS = node.garbageSideCount + countSideGarbage(nextRow, nextCol);
                    if(newG < passByGarbageCount[nextRow][nextCol] ||
                            ((newG == passByGarbageCount[nextRow][nextCol]) && (newS < sideGarbageCount[nextRow][nextCol]))){
                        passByGarbageCount[nextRow][nextCol] = newG;
                        sideGarbageCount[nextRow][nextCol] = newS;
                        pq.add(new Node(nextRow,nextCol,node.dist+1,newG,newS));
                    }
                }
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static class Node implements Comparable<Node>{
        int row, col, dist, garbageCount, garbageSideCount;
        public Node(int row, int col, int dist, int garbageCount, int garbageSideCount) {
            this.row = row;
            this.col = col;
            this.dist = dist;
            this.garbageCount = garbageCount;
            this.garbageSideCount = garbageSideCount;
        }

        @Override
        public int compareTo(Node o) {
            if(garbageCount != o.garbageCount){
                return garbageCount - o.garbageCount;
            } else {
                if(garbageSideCount != o.garbageSideCount){
                    return garbageSideCount - o.garbageSideCount;
                } else {
                    return dist - o.dist;
                }
            }
        }
    }

    static int countSideGarbage(int row, int col){
        if(map[row][col] != '.') return 0; // S/F/g 는 옆쓰레기 카운트 X
        for(int i = 0; i<4; i++){
            int nextRow = row + dRow[i];
            int nextCol = col + dCol[i];
            if(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M){
                if(map[nextRow][nextCol] == 'g') return 1;
            }
        }
        return 0;
    }


}