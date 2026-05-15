import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int len = A.length;
        Arrays.sort(A);
        Arrays.sort(B);
        
        int aIdx = len-1;
        int bIdx = len-1;
        
        int cnt = 0;
        while(aIdx>=0 && bIdx>=0){
            if(A[aIdx] < B[bIdx]){
                cnt++;
                aIdx--;
                bIdx--;
            } else {
                aIdx--;
            }
        }
        
        return cnt;
        
    }
}