import java.util.*;
class Solution {
    public int solution(int n, int[][] edge) {
        //인접 노드 리스트
        ArrayList<Integer>[] adj = new ArrayList[n+1];
        for(int i = 0; i<=n; i++){
            adj[i] = new ArrayList<Integer>();
        }
        
        for(int[] e: edge){
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        
        //최소 거리 배열
        int[] dp = new int[n+1];
        Arrays.fill(dp,n);
        
        //1부터 BFS 시작
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        dp[1] = 0;
        
        int max = 0;
        while(!q.isEmpty()){
            int cur = q.poll();
            
            //인접 노드 중, 이동 거리가 짧은 노드들의 거리를 업데이트 후 큐에 추가
            for(int next: adj[cur]){
                if(dp[next] > dp[cur]+1){
                    dp[next] = dp[cur]+1;
                    q.add(next);
                    
                    //가장 먼 거리 갱신
                    max = Math.max(dp[next], max);
                }
            }
        }
        
        //최대 거리 개수 구한 후, 리턴
        int cnt = 0;
        for(int d: dp){
            if(d == max) cnt++;
        }
        
        return cnt;
        
        
    }
}