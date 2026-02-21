import java.util.*;
class Solution {
    public int solution(int[] citations) {
        int ans = 0;
        Arrays.sort(citations);
        int length = citations.length;
        for(int i = 0; i<length; i++){
            int h = length - i;
            if(h <= citations[i]){
                ans = h;
                break;
            }
        }
        return ans;
    }
}