import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        if(s<n) return new int[]{-1};
        int[] ans = new int[n];
        for(int i = 0; i<n; i++){
            ans[i] = s/n;
        }
        
        for(int i = 0; i< s%n; i++){
            ans[i]++;
        }
        
        Arrays.sort(ans);
        
        return ans;
    }
}