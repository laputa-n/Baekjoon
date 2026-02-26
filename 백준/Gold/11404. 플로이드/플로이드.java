import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 1000000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[][] dist = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                dist[i][j] = INF;
                if(i == j)
                    dist[i][j] = 0;
            }
        }
        for(int i =0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            dist[a][b] = Math.min(dist[a][b], c);
        }

        for(int k = 1; k<=n; k++){
            for(int i=1; i<=n; i++){
                for(int j=1; j<=n; j++){
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        for(int i =1; i<n+1; i++){
            for(int j =1; j<n+1; j++){
                if(dist[i][j] == INF)
                    dist[i][j] = 0;
                bw.write(String.valueOf(dist[i][j]) + " ");
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
