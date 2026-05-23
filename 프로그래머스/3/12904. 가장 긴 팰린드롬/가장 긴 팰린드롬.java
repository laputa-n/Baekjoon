import java.util.*;
class Solution
{
    public int solution(String s)
    {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        
        int maxLen = 1;
        
        for(int i = 0; i<len; i++){
            dp[i][i] = true;
            
            if(i != len-1){
                if(s.charAt(i) == s.charAt(i+1)){
                    dp[i][i+1] = true;
                    maxLen = 2;
                }
            }
        }
        
        
        
        for(int l = 3; l<=len; l++){
            for(int left = 0; left+l-1<len; left++){
                int right = left + l - 1;
                if(dp[left+1][right-1] && s.charAt(left) == s.charAt(right)){
                    dp[left][right] = true;
                    maxLen = l;
                }
            }
        }
        
        return maxLen;
    }
    
}