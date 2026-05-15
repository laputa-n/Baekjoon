import java.util.*;
class Solution {
    static int[] parent;
    static int find(int c){
        if(parent[c] != c){
            parent[c] = find(parent[c]);
        }
        
        return parent[c];
    }
    static void union(int n1, int n2){
        int p1 = find(n1);
        int p2 = find(n2);
        
        if(p1<p2){
            parent[p2] = p1;
        } else {
            parent[p1] = p2;
        }
    }
    public static int solution(int n, int[][] computers){
        parent = new int[n];
        for(int i =0; i<n; i++){
            parent[i] = i;
        }
        
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                if(i==j) continue;
                
                if(computers[i][j] == 1){
                    union(i,j);
                }
            }
        }
        
        Set<Integer> group = new HashSet<>();
        for(int i = 0; i<n; i++){
            group.add(find(i));
        }
        
        return group.size();
    }
}