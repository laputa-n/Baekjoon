import java.util.*;
class Solution {
    public int solution(int n, int[][] costs) {
        boolean[] visited = new boolean[n];
        ArrayList<Point> [] adj = new ArrayList[n];
        for(int i = 0; i<n; i++){
            adj[i] = new ArrayList<Point>();
        }
        for(int[] cost: costs){
            adj[cost[0]].add(new Point(cost[1],cost[2]));
            adj[cost[1]].add(new Point(cost[0],cost[2]));
        }
        int sum = 0;
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.add(new Point(0,0));
        while(!pq.isEmpty()){
            Point p = pq.poll();
            if(visited[p.to]) continue;
            sum += p.cost;
            visited[p.to] = true;

            for(Point po: adj[p.to]){
                if(!visited[po.to])
                    pq.add(po);
            }
            
        }
        return sum;
    }
    static class Point implements Comparable<Point> {
        int to, cost;
        public Point(int t, int c){
            this.to = t;
            this.cost = c;
        }
        
        @Override
        public int compareTo(Point p){
            return this.cost - p.cost;
        }
    }
}