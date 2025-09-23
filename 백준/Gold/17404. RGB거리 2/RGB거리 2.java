import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main{
    static final int INF = 1000000;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[][] cost = new int[N][3];
        String[] input;
        for(int i = 0; i<N; i++){
            input = br.readLine().split(" ");
            cost[i][0] = Integer.parseInt(input[0]);
            cost[i][1] = Integer.parseInt(input[1]);
            cost[i][2] = Integer.parseInt(input[2]);
        }

        int minCost = Integer.MAX_VALUE;

        int[][] dp = new int[N][3];
        for(int start = 0; start<3; start++){
            Arrays.fill(dp[0], INF);
            dp[0][start] = cost[0][start];

            for(int i = 1; i<N; i++){
                dp[i][0] = cost[i][0] + Math.min(dp[i-1][1], dp[i-1][2]);
                dp[i][1] = cost[i][1] + Math.min(dp[i-1][0], dp[i-1][2]);
                dp[i][2] = cost[i][2] + Math.min(dp[i-1][1], dp[i-1][0]);
            }

            for(int end = 0; end<3; end++){
                if(start == end) continue;
                minCost = Math.min(minCost,dp[N-1][end]);
            }
        }

        bw.write(String.valueOf(minCost));
        bw.flush();
        bw.close();
        br.close();
    }
}