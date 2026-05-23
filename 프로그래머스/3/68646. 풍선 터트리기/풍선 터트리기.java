import java.util.*;

class Solution {
    public int solution(int[] a) {
        
        int min = a[0];
        int[] leftMin = new int[a.length];
        for(int i = 0; i<a.length; i++){
            if(a[i] < min)
                min = a[i];
            leftMin[i] = min;
        }
        
        min = a[a.length-1];
        int[] rightMin = new int[a.length];
        for(int i = a.length-1; i>=0; i--){
            if(a[i] < min)
                min = a[i];
            rightMin[i] = min;
        }
        
        int cnt = 0;
        for(int i = 0; i<a.length; i++){
            if(rightMin[i] == a[i] || leftMin[i] == a[i])
                cnt++;
        }
        
        return cnt;
    }
}