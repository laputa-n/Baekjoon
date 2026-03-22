import java.util.*;
class Solution {
    static int[] dRow = {-1,0,1,0};
    static int[] dCol = {0,-1,0,1};
    static final int INF = 10000000;
    public int solution(int[][] maps) {
        int h = maps.length;
        int w = maps[0].length;
        int[][] dp = new int[h][w];
        for(int i = 0; i<h; i++){
            Arrays.fill(dp[i],INF);
        }
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0,0});
        dp[0][0] = 1;
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            if(cur[0] == h-1 && cur[1] == w-1){
                break;
            }
            for(int i = 0; i<4; i++){
                int nextRow = cur[0] + dRow[i];
                int nextCol = cur[1] + dCol[i];
                if(nextRow<0 || nextRow >= h || nextCol<0 || nextCol >= w) continue;
                if(dp[nextRow][nextCol] == INF && maps[nextRow][nextCol] == 1){
                    dp[nextRow][nextCol] = dp[cur[0]][cur[1]] + 1;
                    queue.add(new int[]{nextRow,nextCol});
                }
            }
        }
        if(dp[h-1][w-1] == INF) return -1;
        return dp[h-1][w-1];
    }
}