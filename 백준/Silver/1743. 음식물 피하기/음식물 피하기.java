    import java.io.*;
    import java.util.*;

    public class Main{
        static int[] dRow = {-1,0,1,0};
        static int[] dCol = {0,-1,0,1};
        static int N,M;
        static int[][] map;
        public static void main(String[] args) throws Exception{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            map = new int[N+1][M+1];
            while(K-->0){
                st = new StringTokenizer(br.readLine());
                int row = Integer.parseInt(st.nextToken());
                int col = Integer.parseInt(st.nextToken());
                map[row][col] = 1;
            }
            int res = 0;
            for(int i = 1 ; i <= N ; i++){
                for(int j = 1 ; j <= M ; j++){
                    if(map[i][j] == 1){
                        int size = BFS(i,j);
                        res = Math.max(res,size);
                    }
                }
            }
            bw.write(String.valueOf(res));
            bw.flush();
            bw.close();
            br.close();
        }
        static int BFS(int row, int col){
            Queue<int[]> q = new ArrayDeque<>();
            q.add(new int[]{row,col});
            map[row][col] = 0;
            int cnt = 1;
            while(!q.isEmpty()){
                int[] cur = q.poll();
                for(int i = 0; i < 4; i++){
                    int nextRow = cur[0] + dRow[i];
                    int nextCol = cur[1] + dCol[i];
                    if(nextRow < 1 || nextRow > N || nextCol < 1 || nextCol > M) continue;
                    if(map[nextRow][nextCol] == 1){
                        map[nextRow][nextCol] = 0;
                        q.add(new int[]{nextRow,nextCol});
                        cnt++;
                    }
                }
            }
            return cnt;
        }
    }