import java.util.*;

class Solution {
    static int[] dRow = {-1,0,1,0};
    static int[] dCol = {0,-1,0,1};
    public int solution(int[][] game_board, int[][] table) {
        ArrayList<ArrayList<int[]>> pieces = makePieces(table, 1);
        ArrayList<ArrayList<int[]>> blocks = makePieces(game_board,0);
        
        int sum = 0;
        int piecesCnt = pieces.size();
        
        boolean[] used = new boolean[piecesCnt];
        
        for(ArrayList<int[]> block: blocks){
            for(int i = 0; i<piecesCnt; i++){
                if(used[i]) continue;
                ArrayList<int[]> piece = pieces.get(i);
                boolean flag = false;
                for(int j = 0; j<4; j++){
                    flag = check(block, piece);
                    //포함 안되면 회전
                    if(!flag){
                        piece = rotate(piece);
                    } else break;
                }
                
                if(flag){
                    used[i] = true;
                    sum += block.size();
                    break;
                }
            }
        }
        
        return sum;
    }
    
    static ArrayList<int[]> rotate(ArrayList<int[]> piece){
        //(x,y) -> (y,-x)
        int minRow = 1000;
        int minCol = 1000;
        for(int[] p: piece){
            int tmp = p[0];
            p[0] = p[1];
            p[1] = (-1) * tmp;
            
            minRow = Math.min(minRow,p[0]);
            minCol = Math.min(minCol,p[1]);
        }
        
        for(int[] p:piece){
            p[0] -= minRow;
            p[1] -= minCol;
        }
        
        return piece;
    }
    static boolean check(ArrayList<int[]> block, ArrayList<int[]> piece){
        if(block.size() != piece.size()) return false;
        
        for(int[] bp: block){
            boolean flag = false;
            for(int[] pp:piece){
                if(bp[0] == pp[0] && bp[1] == pp[1]){
                    flag = true;
                    break;
                }
            }
            if(!flag) return false;
        }
        return true;
    }
    
    static ArrayList<ArrayList<int[]>> makePieces(int[][] table, int p){
        ArrayList<ArrayList<int[]>> ans = new ArrayList<>();
        
        int size = table.length;
        boolean[][] visited = new boolean[size][size];
        for(int i = 0; i<size; i++){
            for(int j = 0; j<size; j++){
                if(p == table[i][j] && !visited[i][j]){
                    ArrayList<int[]> piece = new ArrayList<>();
                    Queue<int[]> q = new LinkedList<>();
                    q.add(new int[]{i,j});
                    visited[i][j] = true;
                    
                    int minRow = 1000;
                    int minCol = 1000;
                    while(!q.isEmpty()){
                        int[] cur = q.poll();
                        piece.add(cur);
                        minRow = Math.min(minRow, cur[0]);
                        minCol = Math.min(minCol, cur[1]);
                        
                        for(int k = 0; k<4; k++){
                            int nextRow = cur[0] + dRow[k];
                            int nextCol = cur[1] + dCol[k];
                            
                            if(nextRow < 0 || nextRow >= size || nextCol < 0 || nextCol >= size) continue;
                            if(visited[nextRow][nextCol] || table[nextRow][nextCol] != p) continue;
                            
                            visited[nextRow][nextCol] = true;
                            q.add(new int[]{nextRow,nextCol});
                        }
                    }
                    
                    for(int[] po:piece){
                        po[0] -= minRow;
                        po[1] -= minCol;
                    }
                    ans.add(piece);
                }
            }
        }
        
        return ans;
    }
}