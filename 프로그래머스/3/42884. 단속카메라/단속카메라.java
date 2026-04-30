import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, (r1, r2) -> r1[0] - r2[0]);
        
        int left = routes[0][0];
        int right = routes[0][1];
        
        int cnt = 0;
        
        for(int i = 1; i<routes.length; i++){
            if(routes[i][0] <= right){
                left = routes[i][0];
                right = Math.min(right, routes[i][1]);
            } else {
                cnt++;
                left = routes[i][0];
                right = routes[i][1];
            }
        }
        
        return cnt+1;
    }
}