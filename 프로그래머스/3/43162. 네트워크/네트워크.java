import java.util.*;
class Solution {
    public static int solution(int n, int[][] computers){
        int cnt = 0;
        boolean[] visited = new boolean[n];
        for(int i = 0; i<n; i++){
            if(!visited[i]){
                visited[i] = true;
                cnt++;
                ArrayDeque<Integer> q = new ArrayDeque<Integer>();
                q.add(i);
                while(!q.isEmpty()){
                    int cur = q.poll();
                    for(int j = 0; j<n; j++){
                        if(computers[cur][j] == 1 && !visited[j]){
                            q.add(j);
                            visited[j] = true;
                        }
                    }
                }
            }
        }
        return cnt;
    }
}