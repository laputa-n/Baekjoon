class Solution {
    
    public int solution(int m, int n, int[][] puddles) {
        final int INF = 1000000007;
        //m: 가로, n: 세로
        int[][] dp = new int[n+1][m+1];

        for(int[] p: puddles){
            dp[p[1]][p[0]] = -1;
        }

        dp[1][1] = 1;
        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=m; j++){
                if(dp[i][j] == -1) continue;

                int up = dp[i-1][j];
                int left = dp[i][j-1];

                if(up != -1) dp[i][j] += up%INF;
                if(left != -1) dp[i][j] += left%INF;
            }
        }

        return dp[n][m]%INF;
    }
}