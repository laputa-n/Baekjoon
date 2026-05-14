import java.util.*;
class Solution {
    static int[] parent;
    static int find(int c){
        if(c != parent[c]){
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
    public int solution(int n, int[][] costs) {
        parent = new int[n+1];
        for(int i = 1; i<=n; i++){
            parent[i] = i;
        }
        
        Arrays.sort(costs, (c1, c2) -> c1[2] - c2[2]);
        
        int sum = 0;
        int cnt = 0;
        for(int[] c: costs){
            if(cnt == n-1) break;
            if(find(c[0]) != find(c[1])){
                union(c[0],c[1]);
                sum += c[2];
                cnt++;
            }
        }
        
        return sum;
    }
}