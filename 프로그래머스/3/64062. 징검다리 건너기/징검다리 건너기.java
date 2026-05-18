import java.util.*;
class Solution {
    public int solution(int[] stones, int k) {
        int left = 1;
        int right = 200000000;
        int answer = 0;
        while(left<=right){
            int mid = (left+right)/2;
            
            if(canCross(stones,k,mid)){
                answer = mid;
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        
        return answer;
    }
    static boolean canCross(int[] stones, int k, int people){
        
        int cnt = 0;
        for(int stone: stones){
            if(stone<people) cnt++;
            else cnt = 0;
            if(cnt>=k) return false;
        }
        
        return true;
    }
}