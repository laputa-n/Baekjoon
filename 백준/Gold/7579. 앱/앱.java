import java.io.*;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] memory = new int[N];
        int[] cost = new int[N];

        StringTokenizer memoryInput = new StringTokenizer(br.readLine());
        StringTokenizer costInput = new StringTokenizer(br.readLine());
        int costSum = 0;
        for(int i = 0; i < N; i++){
            memory[i] = Integer.parseInt(memoryInput.nextToken());
            cost[i] = Integer.parseInt(costInput.nextToken());
            costSum += cost[i];
        }

        int[][] dp = new int[N][costSum+1];
        dp[0][cost[0]] = memory[0];
        for(int i = 1; i < N; i++){
            for(int j = 0; j < costSum+1; j++){
                if(j >= cost[i]){
                    dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-cost[i]] + memory[i]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        for(int i = 0; i<=costSum; i++){
            if(dp[N-1][i] >= M){
                bw.write(String.valueOf(i));
                break;
            }
        }



        bw.flush();
        bw.close();
        br.close();
    }
}