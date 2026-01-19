import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] ans = new int[prices.length];
        for(int i = 0; i < prices.length; i++){
            int t = 0;
            for(int j = i+1; j < prices.length; j++){
                t++;
                if(prices[j]<prices[i]){
                    break;
                }
            }
            ans[i] = t;
        }
        return ans;
    }
}