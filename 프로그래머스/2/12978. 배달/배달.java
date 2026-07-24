import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        ArrayList<Node>[] adj = new ArrayList[N+1];
        for(int i = 1; i<=N; i++){
            adj[i] = new ArrayList<Node>();
        }
        
        for(int[] r: road){
            int a = r[0];
            int b = r[1];
            int time = r[2];
            
            adj[a].add(new Node(b,time));
            adj[b].add(new Node(a,time));
        }
        
        int[] dp = new int[N+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 0;
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1,0));
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            
            if(cur.time > dp[cur.to]) continue;
            
            for(Node next: adj[cur.to]){
                if(dp[cur.to] + next.time <= dp[next.to]){
                    dp[next.to] = dp[cur.to] + next.time;
                    pq.add(new Node(next.to, dp[next.to]));
                }
            }
        }
        
        int cnt = 0;
        for(int i = 1; i<= N; i++){
            if(dp[i] <= K) cnt++;
        }
        
        return cnt;
        
    }
    
    static class Node implements Comparable<Node>{
        int to, time;
        
        public Node(int to, int time){
            this.to = to;
            this.time = time;
        }
        
        @Override
        public int compareTo(Node o){
            return Integer.compare(this.time, o.time);
        }
    }
}