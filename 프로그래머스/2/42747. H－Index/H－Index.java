import java.util.*;
// 0 1 3 5 6
// 5 4 3 2 1
class Solution {
    public int solution(int[] citations) {
        int size = citations.length;
        Arrays.sort(citations);
        int ans = 0;
        for(int i = 0; i<size; i++){
            int h = size - i;
            if(citations[i] >= h){
                ans = h;
                break;
            }
        }
        return ans;
    }
}