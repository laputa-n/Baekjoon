import java.util.*;
class Solution {
    public int solution(int[][] triangle) {
        int height = triangle.length;
        
        for(int i = 1; i<height; i++){
            for(int j = 0; j<=i; j++){
                if(j == 0){
                    triangle[i][j] += triangle[i-1][j];
                } else if(j==i){
                    triangle[i][j] += triangle[i-1][j-1];
                } else {
                    triangle[i][j] += Math.max(triangle[i-1][j-1], triangle[i-1][j]);
                }
                
            }
        }
        
        int max = 0;
        for(int l: triangle[height-1]){
            max = Math.max(l,max);
        }
        
        return max;
    }
}