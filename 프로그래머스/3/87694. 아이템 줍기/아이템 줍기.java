import java.util.*;
class Solution {
    static int[] dRow = {-1,0,1,0};
    static int[] dCol = {0,1,0,-1};
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int cnt = Integer.MAX_VALUE;
        int[][] map = new int[101][101];
        for(int[] r:rectangle){
            int leftRow = r[1] * 2;
            int leftCol = r[0] * 2;
            int rightRow = r[3] * 2;
            int rightCol = r[2] * 2;
            for(int i = leftRow; i<=rightRow; i++){
                for(int j = leftCol; j<=rightCol; j++){
                    if(i == leftRow || i == rightRow || j == leftCol || j == rightCol){
                        if(map[i][j] == 1) continue;
                        map[i][j] = 2;
                    } else map[i][j] = 1;
                }
            }
        }
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{characterY*2, characterX*2,0});
        map[characterY*2][characterX*2] = 1;
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            if(cur[0] == itemY*2 && cur[1] == itemX*2){
                cnt = cur[2]/2;
                break;
            }
            for(int i = 0; i<4; i++){
                int nextRow = cur[0] + dRow[i];
                int nextCol = cur[1] + dCol[i];
                if(nextRow < 1 || nextRow >100 || nextCol<1 || nextCol > 100) continue;
                if(map[nextRow][nextCol] == 2){
                    map[nextRow][nextCol] = 1;
                    queue.add(new int[]{nextRow,nextCol,cur[2] + 1});
                }
            }
        }
        return cnt==Integer.MAX_VALUE? -1 : cnt;
        
    }
}