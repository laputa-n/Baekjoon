import java.util.*;

class Solution {
    public int solution(int n, int[] stations, int w) {
        int ans = 0;
        for(int i = 0; i<=stations.length; i++){
            int len = 0;
            if(i == 0){
                len = stations[i] - w - 1;
            } else if (i==stations.length){
                len = n - stations[i-1] - w;
            } else {
                len = stations[i] - 2* w - stations[i-1] - 1;
            }
            
            if(len > 0){
                int cnt = len / (2*w + 1);
                int rem = len % (2*w+1);
                ans += cnt;
                if(rem != 0){
                    ans++;
                }
            }
        }
        return ans;
    }
    
}