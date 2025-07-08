    import java.io.*;
    import java.util.*;

    public class Main {
        static int N,M;
        static int[][] map;
        static int maxSafeCnt = 0;
        static int[] dx = {0,0,1,-1};
        static int[] dy = {1,-1,0,0};
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new int[N][M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            dfs(0);
            bw.write(String.valueOf(maxSafeCnt));
            bw.flush();
            bw.close();
            br.close();
        }
        public static void dfs(int wallCount){
            if(wallCount == 3){
                bfs();
                return;
            }

            for(int i = 0; i<N; i++){
                for(int j = 0; j<M; j++){
                    if(map[i][j] == 0){
                        map[i][j] = 1;
                        dfs(wallCount+1);
                        map[i][j] = 0;
                    }
                }
            }
        }
        public static void bfs(){
            Queue<int[]> q = new LinkedList<>();
            for(int i = 0; i<N; i++){
                for(int j = 0; j<M; j++){
                    if(map[i][j] == 2){
                        q.add(new int[]{i, j});
                    }
                }
            }

            int[][] copyMap = new int[N][M];
            for(int i = 0; i < N; i++){
                copyMap[i] = map[i].clone();
            }
            while(!q.isEmpty()){
                int[] cp= q.poll();
                int curX = cp[0];
                int curY = cp[1];
                for(int i = 0; i<4; i++){
                    int nx = curX + dx[i];
                    int ny = curY + dy[i];
                    if(nx>=0 && nx <N && ny >= 0 && ny <M){
                        if(copyMap[nx][ny] == 0){
                            copyMap[nx][ny] = 2;
                            q.add(new int[]{nx, ny});
                        }
                    }
                }
            }
            cntSafeZone(copyMap);
        }
        public static void cntSafeZone(int[][] copyMap){
            int cnt = 0;
            for(int i = 0; i<N; i++){
                for(int j = 0; j<M; j++){
                    if(copyMap[i][j] == 0){
                        cnt++;
                    }
                }
            }
            if(cnt>maxSafeCnt){
                maxSafeCnt = cnt;
            }
        }
    }

