import java.io.*;
import java.util.*;

public class Main {
    static int[] dRow = {-1,0,1,0};
    static int[] dCol = {0,1,0,-1};
    static int N,M;
    static List<CCTV> cctvList;
    static int blindspot;
    static void shoot(int row, int col, int dir, int[][] map){
        int curRow = row;
        int curCol = col;
        while(curRow+dRow[dir]>=0 && curRow+dRow[dir] < N &&  curCol+dCol[dir]>= 0 && curCol+dCol[dir] < M){
            curRow = curRow+dRow[dir];
            curCol = curCol+dCol[dir];
            if(map[curRow][curCol] == 6)
                break;
            if(map[curRow][curCol] == 0){
                map[curRow][curCol] = 7;
            }
        }
    }
    static int countBS(int[][] map){
        int cnt = 0;
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                if(map[i][j] == 0)
                    cnt++;
            }
        }
        return cnt;
    }
    static void DFS(int operCnt, int[][] map){
        if(operCnt == cctvList.size()){
            blindspot = Math.min(blindspot,countBS(map));
            return;
        }


        CCTV curCCTV = cctvList.get(operCnt);
        int curType = curCCTV.type;
        if(curType == 1){
            for(int i =0; i<4; i++){
                int[][] temp = new int[N][M];
                for(int k = 0; k<N; k++){
                    temp[k] = map[k].clone();
                }
                shoot(curCCTV.row,curCCTV.col,i,temp);
                DFS(operCnt+1, temp);
            }
        } else if(curType == 2){
            for(int i = 0; i<2; i++){
                int[][] temp = new int[N][M];
                for(int k = 0; k<N; k++){
                    temp[k] = map[k].clone();
                }
                shoot(curCCTV.row,curCCTV.col,i,temp);
                shoot(curCCTV.row,curCCTV.col,i+2,temp);
                DFS(operCnt+1, temp);
            }
        } else if(curType == 3){
            for(int i = 0; i<4; i++){
                int[][] temp = new int[N][M];
                for(int k = 0; k<N; k++){
                    temp[k] = map[k].clone();
                }
                shoot(curCCTV.row,curCCTV.col,i,temp);
                shoot(curCCTV.row,curCCTV.col,(i+1)%4,temp);
                DFS(operCnt+1, temp);
            }
        } else if(curType == 4){
            for(int i = 0; i<4; i++){
                int[][] temp = new int[N][M];
                for(int k = 0; k<N; k++){
                    temp[k] = map[k].clone();
                }
                shoot(curCCTV.row,curCCTV.col,i%4,temp);
                shoot(curCCTV.row,curCCTV.col,(i+1)%4,temp);
                shoot(curCCTV.row,curCCTV.col,(i+2)%4,temp);
                DFS(operCnt+1, temp);
            }
        } else if(curType == 5){
            int[][] temp = new int[N][M];
            for(int k = 0; k<N; k++){
                temp[k] = map[k].clone();
            }
            shoot(curCCTV.row,curCCTV.col,0,temp);
            shoot(curCCTV.row,curCCTV.col,1,temp);
            shoot(curCCTV.row,curCCTV.col,2,temp);
            shoot(curCCTV.row,curCCTV.col,3,temp);
            DFS(operCnt+1, temp);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        cctvList = new ArrayList<>();
        blindspot = N*M;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] >= 1 && map[i][j] <= 5){
                    cctvList.add(new CCTV(i,j,map[i][j]));
                }
            }
        }
        DFS(0,map);
        bw.write(String.valueOf(blindspot));
        bw.flush();
        bw.close();
        br.close();
    }
    static class CCTV {
        int row,col,type,mode;
        CCTV(int row,int col,int type) {
            this.row = row;
            this.col = col;
            this.type = type;
        }
    }
}
