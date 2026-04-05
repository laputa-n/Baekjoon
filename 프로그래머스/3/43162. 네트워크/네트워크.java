import java.util.*;
class Solution {
    static boolean[] visited;
    public static int solution(int n, int[][] computers){
        visited = new boolean[n];
        int cnt = 0;
        for(int i = 0; i<n; i++){
            if(!visited[i]){
                cnt++;
                DFS(i, computers, n);
            }
        }
        return cnt;
    }
    static void DFS(int tar, int[][] computers, int n){
        visited[tar] = true;
        for(int i = 0; i<n; i++){
            if(computers[tar][i] == 1 && !visited[i]){
                DFS(i, computers, n);
            }
        }
    }
}