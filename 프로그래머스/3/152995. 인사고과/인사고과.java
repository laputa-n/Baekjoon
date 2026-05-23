import java.util.*;
class Solution {
    public int solution(int[][] scores) {
        int wanhoA = scores[0][0];
        int wanhoB = scores[0][1];
        int wanhoSum = wanhoA + wanhoB;
        
        Arrays.sort(scores, (s1,s2) -> {
            if(s1[0] == s2[0]){
                return Integer.compare(s1[1],s2[1]);
            }
            
            return Integer.compare(s2[0],s1[0]);
        });
        
        int rank = 1;
        int maxB = -1;
        
        for(int[] score: scores){
            int a = score[0];
            int b = score[1];
            
            if(b < maxB){
                if(a == wanhoA && b == wanhoB)
                    return -1;
                
                continue;
            }
            
            maxB = Math.max(maxB,b);
            
            if(a+b > wanhoSum)
                rank++;
        }
        
        return rank;
    }
}