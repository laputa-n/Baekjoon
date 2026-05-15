import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int carCnt = routes.length;
        int cameraCnt = 1;
        Arrays.sort(routes,(r1, r2) -> r1[0]-r2[0]);
        
        int left = routes[0][0];
        int right = routes[0][1];
        
        for(int i = 1; i<carCnt; i++){
            if(right < routes[i][0]){
                cameraCnt++;
                left = routes[i][0];
                right = routes[i][1];
            } else {
                left = routes[i][0];
                right = Math.min(right, routes[i][1]);
            }
        }
        
        return cameraCnt;
        
        
    }
}