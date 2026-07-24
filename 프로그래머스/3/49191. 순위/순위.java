import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        int[][] matrix = new int[n+1][n+1];
        
        for(int[] result: results){
            int win = result[0];
            int lose = result[1];
            
            matrix[win][lose] = 1;
            matrix[lose][win] = -1;
        }
        
        for(int k = 1; k<=n; k++){
            for(int i = 1; i<=n; i++){
                for(int j = 1; j<=n; j++){
                    if(matrix[i][k] == 1 && matrix[k][j] == 1){
                        matrix[i][j] = 1;
                        matrix[j][i] = -1;
                    }
                    
                    //i가 k한테 질때
                    if(matrix[i][k] == -1 && matrix[k][j] == -1){
                        matrix[i][j] = -1;
                        matrix[j][i] = 1;
                    }
                }
            }
        }
        
        int cnt = 0;
        for(int i = 1; i<=n; i++){
            boolean canCal = true;
            
            for(int j = 1; j<=n; j++){
                if(i == j) continue;
                
                if(matrix[i][j] == 0){
                    canCal = false;
                    break;
                }
            }
            
            if(canCal) cnt++;
        }
        
        return cnt;
    }
        
}