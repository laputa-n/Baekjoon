import java.io.*;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] T = new int[N+2];
        int[] P = new int[N+2];
        StringTokenizer st;
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }
        int[] dp = new int[N+2];
        int max = Integer.MIN_VALUE;
        for(int i = 1; i <= N+1; i++){
            if(max <dp[i]) max = dp[i];

            int nxt = i+T[i];
            if(nxt < N+2){
                dp[nxt] = Math.max(dp[nxt], max + P[i]);
            }
        }
        bw.write(String.valueOf(dp[N+1]));
        bw.flush();
        bw.close();
        br.close();
    }
}