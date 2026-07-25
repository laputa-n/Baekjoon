import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        //destination에서 sources로 가는 최단거리
        ArrayList<Integer>[] adj = new ArrayList[n+1];
        for(int i = 1; i<=n; i++){
            adj[i] = new ArrayList<>();
        }
        for(int[] road: roads){
            int a = road[0];
            int b = road[1];
            
            adj[a].add(b);
            adj[b].add(a);
        }
        
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[destination] = 0;
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(destination, 0));
        
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            
            if(dp[cur.to] < cur.dist) continue;
            
            for(int next: adj[cur.to]){
                if(dp[next] >= dp[cur.to] + 1){
                    dp[next] = dp[cur.to] + 1;
                    pq.add(new Node(next, dp[next]));
                }
            }
        }
        
        int[] answer = new int[sources.length];
        
        for(int i = 0; i<sources.length; i++){
            answer[i] = dp[sources[i]] == Integer.MAX_VALUE? -1 : dp[sources[i]];
        }
        
        return answer;
        
    }
    static class Node implements Comparable<Node> {
        int to, dist;
        
        public Node(int to, int dist){
            this.to = to;
            this.dist = dist;
        }
        
        @Override
        public int compareTo(Node o){
            return Integer.compare(this.dist, o.dist);
        }
    }
}