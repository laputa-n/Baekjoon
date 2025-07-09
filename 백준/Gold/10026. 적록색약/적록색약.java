    import java.io.*;
    import java.util.*;

    public class Main {
        static int[] dx = {-1, 0, 1, 0};
        static int[] dy = {0, 1, 0, -1};
        static int N;
        static char[][] map;
        static int[][] cbZone, ncbZone;
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            N = Integer.parseInt(br.readLine());
            map = new char[N][N];
            for (int i = 0; i < N; i++) {
                map[i] = br.readLine().toCharArray();
            }
            cbZone = new int[N][N];
            ncbZone = new int[N][N];
            int cbCnt = 1;
            int ncbCnt = 1;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(cbZone[i][j] == 0) {
                        cbDfs(i,j,cbCnt++);
                    }
                    if(ncbZone[i][j] == 0) {
                        ncbDfs(i,j,ncbCnt++);
                    }
                }
            }
            bw.write(String.valueOf(--ncbCnt) + " " + String.valueOf(--cbCnt));
            bw.flush();
            bw.close();
            br.close();
        }
        static void ncbDfs(int row, int col, int cnt){
            Queue<int[]> q = new LinkedList<>();
            q.add(new int[]{row, col});
            char curColor = map[row][col];
            ncbZone[row][col] = cnt;
            while(!q.isEmpty()){
                int[] cur = q.poll();
                int curRow = cur[0];
                int curCol = cur[1];
                for(int i = 0; i<4; i++){
                    int nRow = curRow + dx[i];
                    int nCol = curCol + dy[i];
                    if(nRow >= 0 && nRow < N && nCol >= 0 && nCol < N){
                        if(map[nRow][nCol] == curColor && ncbZone[nRow][nCol] == 0){
                            q.add(new int[]{nRow, nCol});
                            ncbZone[nRow][nCol] = cnt;
                        }
                    }
                }
            }
        }

        static void cbDfs(int row, int col, int cnt){
            Queue<int[]> qq = new LinkedList<>();
            qq.add(new int[]{row, col});
            boolean rg = map[row][col] != 'B';
            cbZone[row][col] = cnt;
            while(!qq.isEmpty()){
                int[] cur = qq.poll();
                int curRow = cur[0];
                int curCol = cur[1];
                for(int i = 0; i<4; i++){
                    int nRow = curRow + dx[i];
                    int nCol = curCol + dy[i];
                    if(nRow >= 0 && nRow < N && nCol >= 0 && nCol < N){
                        if(cbZone[nRow][nCol] == 0){
                            if(rg){
                                if(map[nRow][nCol] == 'R' || map[nRow][nCol] == 'G'){
                                    qq.add(new int[]{nRow, nCol});
                                    cbZone[nRow][nCol] = cnt;
                                }
                            } else {
                                if(map[nRow][nCol] == 'B'){
                                    qq.add(new int[]{nRow, nCol});
                                    cbZone[nRow][nCol] = cnt;
                                }
                            }
                        }
                    }
                }
            }
        }
    }


