    import java.io.*;
    import java.util.*;

    public class Main {
        static int[] dx = {-1, 0, 1, 0, 0, 0};
        static int[] dy = {0, -1, 0, 1, 0, 0};
        static int[] dz = {0, 0, 0, 0, 1, -1};
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());
            int[][][] matrix = new int[N][M][H];
            int totalCnt = 0;
            int curCnt = 0;
            Queue<int[]> q = new LinkedList<>();
            for(int i = 0; i < H; i++){
                for(int j = 0; j < N; j++){
                    st = new StringTokenizer(br.readLine(), " ");
                    for(int k = 0; k < M; k++){
                        matrix[j][k][i] = Integer.parseInt(st.nextToken());
                        if(matrix[j][k][i] != -1) totalCnt++;
                        if(matrix[j][k][i] == 1) {
                            curCnt++;
                            q.add(new int[]{j, k, i});
                        }
                    }
                }
            }
            int dayCnt = 0;
            while(!q.isEmpty()){
                int size = q.size();
                for(int i = 0; i < size; i++){
                    int[] cur = q.poll();
                    int curRow = cur[0];
                    int curCol = cur[1];
                    int curHeight = cur[2];
                    for(int j = 0; j<6; j++){
                        int newRow = curRow + dx[j];
                        int newCol = curCol + dy[j];
                        int newHeight = curHeight + dz[j];
                        if(newRow >= 0 && newRow < N
                        && newCol >= 0 && newCol < M
                        && newHeight >= 0 && newHeight < H){
                           if(matrix[newRow][newCol][newHeight] == 0){
                               matrix[newRow][newCol][newHeight] = 1;
                               q.add(new int[]{newRow, newCol, newHeight});
                               curCnt++;
                           }
                        }
                    }
                }
                dayCnt++;
            }
            bw.write(totalCnt == curCnt?String.valueOf(--dayCnt):"-1");
            bw.flush();
            bw.close();
            br.close();
        }
    }



