import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] ans = new int[sources.length];
        
        ArrayList<Integer>[] adj = new ArrayList[n+1];
        for(int i = 1; i<=n; i++){
            adj[i] = new ArrayList<>();
        }
        
        for(int[] road : roads){
            adj[road[0]].add(road[1]);
            adj[road[1]].add(road[0]);
        }
        
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        
        Queue<Integer> q = new LinkedList<>();
        q.add(destination);
        dp[destination] = 0;
        while(!q.isEmpty()){
            int cur = q.poll();
            for(int next: adj[cur]){
                if(dp[cur] + 1 < dp[next]){
                    dp[next] = dp[cur] + 1;
                    q.add(next);
                }
            }
        }
        
        for(int i = 0; i<sources.length; i++){
            int res = dp[sources[i]];
            ans[i] = res == Integer.MAX_VALUE? -1: res;
        }
        
        return ans;
    }
}