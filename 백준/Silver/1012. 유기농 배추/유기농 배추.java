    import java.util.*;
    import java.io.*;

    public class Main {
        static int[][] arr;
        static int[] dx;
        static int[] dy;
        static int N;
        static int M;
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            //        솔루션 코드 작성
            int T = Integer.parseInt(br.readLine());
            dx = new int[]{1,0,-1,0};
            dy = new int[]{0, 1, 0, -1};
            for (int t = 0; t < T; t++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                M = Integer.parseInt(st.nextToken());
                N = Integer.parseInt(st.nextToken());
                int K = Integer.parseInt(st.nextToken());
                int cnt = 0;
                arr = new int[N][M];
                for(int k = 0; k < K; k++){
                    st = new StringTokenizer(br.readLine());
                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());
                    arr[y][x] = 1;
                }
                for(int u = 0; u < N; u++){
                    for(int v = 0; v < M; v++){
                        if(arr[u][v] == 1){
                            dfs(u,v);
                            cnt++;
                        }
                    }
                }
                bw.write(String.valueOf(cnt) + "\n");
            }
            bw.flush();
            bw.close();
            br.close();
        }
        public static void dfs(int a, int b){
            arr[a][b] = 0;
            for(int i = 0 ; i<4; i++){
                int nx = a + dx[i];
                int ny = b + dy[i];
                if(nx >= 0 && nx <= N-1 && ny >=0 && ny <= M-1 && arr[nx][ny] == 1){
                    dfs(nx,ny);
                }
            }
        }
    }