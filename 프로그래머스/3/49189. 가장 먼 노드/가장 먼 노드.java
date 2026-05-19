import java.util.*;
class Solution {
    public int solution(int n, int[][] edge) {
        
        //인접리스트 배열 선언 및 초기화
        ArrayList<Integer>[] adj = new ArrayList[n+1];
        for(int i = 1; i<=n; i++){
            adj[i] = new ArrayList<>();
        }
        
        //인접 노드 추가
        for(int[] e: edge){
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        
        //1에서 최단거리 배열 dp 선언 및 초기화
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        
        //다익스트라 진행하며 가장 먼 거리 업데이트
        int maxDist = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1,0));
        dp[1] = 0;
        
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            
            if(dp[cur.to] < cur.dist) continue;
            
            for(int next: adj[cur.to]){
                if(dp[next] > cur.dist+1){
                    dp[next] = cur.dist+1;
                    pq.add(new Node(next, dp[next]));
                    maxDist = Math.max(maxDist, dp[next]);
                }
            }
        }
        
        //가장 먼 노드 카운트
        int cnt = 0;
        for(int i = 2; i<=n; i++){
            if(dp[i] == maxDist)
                cnt++;
        }
        
        return cnt;
    }
    static class Node implements Comparable<Node>{
        int to, dist;
        
        public Node(int t, int d){
            this.to = t;
            this.dist = d;
        }
        
        @Override
        public int compareTo(Node n){
            return Integer.compare(dist, n.dist);
        }
    }
}