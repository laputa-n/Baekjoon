import java.util.*;

class Solution {
    public int solution(String arr[]) {
        int len = arr.length;
        int[] num = new int[len/2+1];
        char[] oper = new char[len/2];
        
        for(int i = 0; i<len; i++){
            if(i%2 == 0){
                num[i/2] = Integer.parseInt(arr[i]);
            } else {
                oper[i/2] = arr[i].charAt(0);
            }
        }
        
        int[][] maxDp = new int[len/2+1][len/2+1];
        int[][] minDp = new int[len/2+1][len/2+1];
        for(int i = 0; i<len/2+1; i++){
            for(int j = 0; j<len/2+1; j++){
                maxDp[i][j] = Integer.MIN_VALUE;
                minDp[i][j] = Integer.MAX_VALUE;
            }
        }
        
        for(int d = 0; d<num.length; d++){
            for(int i = 0; i<num.length-d; i++){
                int j = i+d;
                if(i == j){
                    maxDp[i][i] = num[i];
                    minDp[i][i] = num[i];
                    continue;
                }
                
                for(int k = i; k<j; k++){
                    if(oper[k] == '+'){
                        maxDp[i][j] = Math.max(maxDp[i][j], maxDp[i][k] + maxDp[k+1][j]);
                        minDp[i][j] = Math.min(minDp[i][j], minDp[i][k] + minDp[k+1][j]);
                    } else {
                        maxDp[i][j] = Math.max(maxDp[i][j], maxDp[i][k] - minDp[k+1][j]);
                        minDp[i][j] = Math.min(minDp[i][j], minDp[i][k] - maxDp[k+1][j]);
                    }
                }
            }
        }
        
        return maxDp[0][len/2];
        
    }
}