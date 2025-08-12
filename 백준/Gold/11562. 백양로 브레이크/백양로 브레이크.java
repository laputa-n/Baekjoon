import java.io.*;
import java.util.*;
public class Main
{
    static final int INF = 1000000;
    public static void main(String[] args) throws Exception
    {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] dp = new int[n+1][n+1];
        for(int i = 1; i <= n; i++){
            Arrays.fill(dp[i], INF);
            dp[i][i] = 0;
        }

        while(m-->0){
            int u = sc.nextInt();
            int v = sc.nextInt();
            int b = sc.nextInt();
            dp[u][v] = 0;
            if(b == 1){
                dp[v][u] = 0;
            } else {
                dp[v][u] = 1;
            }
        }
        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    dp[i][j] = Math.min(dp[i][j],dp[i][k]+dp[k][j]);
                }
            }
        }

        int k = sc.nextInt();
        while(k-->0){
            int s = sc.nextInt();
            int e = sc.nextInt();
            System.out.println(dp[s][e]);
        }
//        bw.flush();
//        bw.close();
//        br.close();
    }
}