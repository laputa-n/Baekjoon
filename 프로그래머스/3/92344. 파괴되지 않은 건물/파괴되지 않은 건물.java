
class Solution {
    public int solution(int[][] board, int[][] skill) {
        int height = board.length;
        int width = board[0].length;
        
        int[][] diff = new int[height+1][width+1];
        for(int[] s: skill){
            int type = s[0];
            int r1 = s[1];
            int c1 = s[2];
            int r2 = s[3];
            int c2 = s[4];
            int degree = s[5];
            
            if(type == 1) degree *= -1;
            
            diff[r1][c1] += degree;
            diff[r2+1][c1] -= degree;
            diff[r1][c2+1] -= degree;
            diff[r2+1][c2+1] += degree;
        }
        
        for(int i = 0; i<=height; i++){
            for(int j = 1; j<=width; j++){
                diff[i][j] += diff[i][j-1];
            }
        }
        
        for(int i = 0; i<=width; i++){
            for(int j = 1; j<=height; j++){
                diff[j][i] += diff[j-1][i];
            }
        }
        
        for(int i = 0; i<height; i++){
            for(int j = 0; j<height; j++){
                board[i][j] += diff[i][j];
            }
        }
        
        int cnt = 0;
        for(int i = 0; i<height; i++){
            for(int j = 0; j<width; j++){
                if(board[i][j]>0) cnt++;
            }
        }
        return cnt;
    }
}