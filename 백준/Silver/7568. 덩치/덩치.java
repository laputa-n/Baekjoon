    import java.io.*;
    import java.util.*;

    public class Main {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            int N = Integer.parseInt(br.readLine());
            int[][] matrix = new int[N][2];
            int[] rank = new int[N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine()," ");
                matrix[i][0] = Integer.parseInt(st.nextToken());
                matrix[i][1] = Integer.parseInt(st.nextToken());
            }
            for(int i = 0; i < N; i++) {
                int cnt = 1;
                for(int j = 0; j < N; j++) {
                    if(i == j) continue;
                    if(matrix[i][0] < matrix[j][0] && matrix[i][1] < matrix[j][1]) cnt++;
                }
                rank[i] = cnt;
            }
            for(int r:rank){
                bw.write(String.valueOf(r) + " ");
            }
            bw.flush();
            bw.close();
            br.close();

        }
    }
