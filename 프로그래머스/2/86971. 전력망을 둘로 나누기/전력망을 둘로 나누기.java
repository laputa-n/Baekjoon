import java.util.*;
class Solution {
    static ArrayList<Integer>[] adj;
    static int min;
    public int solution(int n, int[][] wires) {
        min = n;
        adj = new ArrayList[n+1];
        for(int i = 0; i<=n; i++){
            adj[i] = new ArrayList<Integer>();
        }
        for(int[] wire: wires){
            adj[wire[0]].add(wire[1]);
            adj[wire[1]].add(wire[0]);
        }
        
        for(int i = 0; i<n-1; i++){
            if(min == 0) break;
            int a = wires[i][0];
            int b = wires[i][1];
            adj[a].remove(Integer.valueOf(b));
            adj[b].remove(Integer.valueOf(a));
            
            boolean[] visited = new boolean[n+1];
            Queue<Integer> q = new LinkedList<>();
            q.add(1);
            visited[1] = true;
            int cnt = 1;
            while(!q.isEmpty()){
                int cur = q.poll();
                for(int next: adj[cur]){
                    if(!visited[next]){
                        q.add(next);
                        visited[next] = true;
                        cnt++;
                    }
                }
            }
            min = Math.min(min, Math.abs(cnt - (n-cnt)));
            
            adj[a].add(b);
            adj[b].add(a);
        }
        
        return min;
    }
}