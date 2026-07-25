import java.util.*;

class Solution {
    static final int INF = 20000001;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] dp = new int[n+1][n+1];
        
        for(int i = 0; i<= n; i++){
            Arrays.fill(dp[i], INF);
            dp[i][i] = 0;
        }
        
        for(int[] fare: fares){
            int c = fare[0];
            int d = fare[1];
            int cost = fare[2];
            dp[c][d] = dp[d][c] = cost;
        }
        
        for(int k = 1; k<=n; k++){
            for(int i = 1; i<=n; i++){
                for(int j = 1; j<=n; j++){
                    dp[i][j] = Math.min(dp[i][k] + dp[k][j], dp[i][j]);
                }
            }
        }
        
        //#1 출발지 -> A -> B
        //#2 출발지 -> B -> A (위와 동일한 건 A-B, 출발지에서 A로 가냐, B로 가냐 차이)
        //#3 출발지 -> 다른 곳에서 둘 다 내린 후, 각자 집까지
        int answer = dp[a][b] + Math.min(dp[s][a], dp[s][b]);
        for(int i = 1; i<=n; i++){
            if(i == a || i == b) continue;
            answer = Math.min(answer, dp[s][i] + dp[i][a] + dp[i][b]);
        }
        
        return answer;
    }
}