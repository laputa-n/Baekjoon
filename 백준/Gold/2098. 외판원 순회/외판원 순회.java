import java.io.*;
import java.util.*;

public class Main {
    static int[][] W,dp;
    static int N,statusFullBit;
    static final int INF = 16*1000000 + 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        W = new int[N][N];
        statusFullBit = (1<<N) - 1;
        dp = new int[N][statusFullBit];
        StringTokenizer st;
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++){
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 0; i<N; i++){
            Arrays.fill(dp[i], -1);
        }
        bw.write(String.valueOf(TSP(0,1)));
        bw.flush();
        bw.close();
        br.close();
    }
    static int TSP(int cur, int curState){
        if(curState == statusFullBit){
            if(W[cur][0] == 0)
                return INF;
            else return W[cur][0];
        }

        if(dp[cur][curState] != -1) return dp[cur][curState];

        dp[cur][curState] = INF;

        for(int i = 0; i<N; i++){
            int next = curState | (1<<i);
            if(W[cur][i] == 0 || (curState & (1<<i)) != 0) continue;
            dp[cur][curState] = Math.min(dp[cur][curState], TSP(i,next) + W[cur][i]);
        }
        return dp[cur][curState];
    }
}
