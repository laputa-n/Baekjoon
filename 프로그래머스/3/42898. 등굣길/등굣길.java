class Solution {
    static final int INF =  1000000007;
    public int solution(int m, int n, int[][] puddles) {
        int[][] map = new int[n][m];
        
        for(int[] puddle: puddles){
            int col = puddle[0]-1;
            int row = puddle[1]-1;
            
            map[row][col] = -1;
        }
        
        for(int i = 0; i<m; i++){
            if(map[0][i] == -1) break;
            map[0][i] = 1;
        }
        
        for(int i = 0; i<n; i++){
            if(map[i][0] == -1) break;
            map[i][0] = 1;
        }
        
        for(int i = 1; i<n; i++){
            for(int j = 1; j<m; j++){
                if(map[i][j] == -1) continue;
                
                if(map[i-1][j] != -1) map[i][j] += map[i-1][j]%INF;
                if(map[i][j-1] != -1) map[i][j] += map[i][j-1]%INF;
            }
        }
        
        return map[n-1][m-1]%INF;
        
        
    }
}