import java.util.*;

class Solution {
    public long solution(int[] sequence) {
        long prefix = 0;
        
        long minPrefix = 0;
        long maxPrefix = 0;
        
        long answer = 0;
        
        for(int i = 0; i<sequence.length; i++){
            long value = sequence[i];
            
            if(i%2 == 0) value *= -1;
            
            prefix += value;
            
            answer = Math.max(answer, prefix - minPrefix);
            answer = Math.max(answer, maxPrefix - prefix);
            
            minPrefix = Math.min(minPrefix, prefix);
            maxPrefix = Math.max(maxPrefix, prefix);
        }
        
        return answer;
    }
}