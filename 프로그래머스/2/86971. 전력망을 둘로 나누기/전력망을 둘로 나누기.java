import java.util.*;
class Solution {
    static int[] parent;
    public int solution(int n, int[][] wires) {
        int ans = Integer.MAX_VALUE;
        parent = new int[n+1];

        for(int i = 0; i<n-1; i++){
            for(int j = 0; j < n+1; j++){
                parent[j] = j;
            }
            for(int j = 0; j<n-1; j++){
                if(i == j) continue;
                union(wires[j][0],wires[j][1]);
            }
            Set<Integer> set = new HashSet<>();
            for(int j = 1; j<n+1; j++){
                set.add(find(j));
            }
            int num1 = (int) set.toArray()[0];
            int num2 = (int) set.toArray()[1];
            int cnt1 = 0;
            int cnt2 = 0;
            for(int j = 1; j<n+1; j++){
                if(parent[j] == num1) cnt1++;
                if(parent[j] == num2) cnt2++;
            }
            if(cnt1 == cnt2) return 0;
            ans = Math.min(ans,Math.abs(cnt1 - cnt2));
        }
        return ans==Integer.MAX_VALUE?-1:ans;
    }
    static int find(int num){
        if(parent[num] == num) return num;
        return parent[num] = find(parent[num]);
    }

    static void union(int num1, int num2){
        int root1 = find(num1);
        int root2 = find(num2);
        if(root1 < root2){
            parent[root2] = root1;
        } else {
            parent[root1] = root2;
        }
    }
}