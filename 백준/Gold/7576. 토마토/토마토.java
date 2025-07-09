    import java.io.*;
    import java.util.*;

    public class Main {
        static int[] dx = {-1, 0, 1, 0};
        static int[] dy = {0, 1, 0, -1};
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int[][] matrix = new int[N][M];
            Queue<int[]> q = new LinkedList<>();
            int totalTomatoCnt = 0;
            int curTomatoCnt = 0;
            for(int i = 0; i<N; i++){
                st = new StringTokenizer(br.readLine()," ");
                for(int j = 0; j<M; j++){
                    matrix[i][j] = Integer.parseInt(st.nextToken());
                    if(matrix[i][j] != -1){
                        totalTomatoCnt++;
                    }
                    if(matrix[i][j] == 1){
                        q.add(new int[]{i, j});
                        curTomatoCnt++;
                    }
                }
            }
            int dayCnt = 0;
            while(!q.isEmpty()){
                int size = q.size();
                for(int i = 0; i<size; i++){
                    int[] cur = q.poll();
                    for(int j = 0; j<4; j++){
                        int nx = cur[0] + dx[j];
                        int ny = cur[1] + dy[j];
                        if(nx >= 0 && nx < N && ny >= 0 && ny < M){
                            if(matrix[nx][ny] == 0){
                                matrix[nx][ny] = 1;
                                q.add(new int[]{nx, ny});
                                curTomatoCnt++;
                            }
                        }
                    }
                }
                dayCnt++;
            }
            dayCnt--;
            if(totalTomatoCnt != curTomatoCnt){
                bw.write("-1");
            } else {
                bw.write(String.valueOf(dayCnt));
            }
            bw.flush();
            bw.close();
            br.close();
        }
    }


