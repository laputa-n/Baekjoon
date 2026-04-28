import java.util.*;

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] ans = new int[2];
        for(int i = 1; i<=yellow; i++){
            if(yellow%i != 0) continue;
            int w = Math.max(i,yellow/i);
            int h = Math.min(i,yellow/i);
            if(brown == (w + h + 2)*2){
                ans[0] = w+2;
                ans[1] = h+2;
            }
        }
        return ans;
    }
}