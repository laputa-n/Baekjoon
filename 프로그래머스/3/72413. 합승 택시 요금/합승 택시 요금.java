import java.util.*;

class Solution {
    static final int INF = 20000001;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        //#1 중간 어느 곳까지 같이 가고, 거기서 각자 가기
        //#2 처음부터 따로 가기
        //#3 S->A->B
        //#4 S->B->A
        int[][] dp = new int[n+1][n+1];
        for(int i = 0; i<=n; i++){
            Arrays.fill(dp[i], INF);
            dp[i][i] = 0;
        }
        
        for(int[] fare: fares){
            int start = fare[0];
            int end = fare[1];
            int cost = fare[2];
            
            dp[start][end] = cost;
            dp[end][start] = cost;
        }
        
        for(int k = 1; k<=n; k++){
            for(int i = 1; i<=n; i++){
                for(int j = 1; j<=n; j++){
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }
        
        int case1 = Integer.MAX_VALUE;
        for(int i = 1; i<=n; i++){
            if(i == s || i == a || i == b) continue;
            case1 = Math.min(case1, dp[s][i] + dp[i][a] + dp[i][b]);
        }
        
        int case2 = dp[s][a] + dp[s][b];
        int case3 = dp[s][a] + dp[a][b];
        int case4 = dp[s][b] + dp[b][a];
        int answer = Math.min(Math.min(Math.min(case1,case2), case3), case4);
        return answer;
    }
}