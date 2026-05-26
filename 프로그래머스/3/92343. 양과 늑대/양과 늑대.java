import java.util.*;

class Solution {
    static int[] parent;
    static ArrayList<Integer>[] childs;
    static int maxSheepCnt;
    static boolean[] visited;
    
    public int solution(int[] info, int[][] edges) {
        //info 0->양, 1-> 늑대
        int N = info.length;
        
        maxSheepCnt = 1;
        parent = new int[N];
        childs = new ArrayList[N];
        for(int i = 0; i<N; i++){
            childs[i] = new ArrayList<>();
        }
        
        for(int[] edge: edges){
            int p = edge[0];
            int c = edge[1];
            parent[c] = p;
            childs[p].add(c);
        }
        
        //num, sheepCnt, wolfCnt, visited, info
        visited = new boolean[N];
        visited[0] = true;
        DFS(0, 1, 0, info);
        
        return maxSheepCnt;
    }
    static void DFS(int num, int sheepCnt, int wolfCnt, int[] info){
        maxSheepCnt = Math.max(maxSheepCnt, sheepCnt);
        
        for(int i = 0; i<info.length; i++){
            if(visited[i]){
                for(int child: childs[i]){
                    if(!visited[child]){
                        int nextSheepCnt = sheepCnt;
                        int nextWolfCnt = wolfCnt;
                        if(info[child] == 1){
                            nextWolfCnt++;
                        } else {
                            nextSheepCnt++;
                        }
                        if(nextSheepCnt > nextWolfCnt){
                            visited[child] = true;
                            DFS(child, nextSheepCnt, nextWolfCnt, info);
                            visited[child] = false;
                        }
                    }
                }
            }
        }
    }
}