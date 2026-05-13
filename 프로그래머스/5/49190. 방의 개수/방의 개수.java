import java.util.*;
class Solution {
    static int[] dRow = {-1,-1,0,1,1,1,0,-1};
    static int[] dCol = {0,1,1,1,0,-1,-1,-1};
    
    public int solution(int[] arrows) {
        int answer = 0;
        
        // row,col
        Set<String> visitedNode = new HashSet<>();
        //r,c -> nextRow,nextCol
        Set<String> visitedEdge = new HashSet<>();
        
        int curRow = 0;
        int curCol = 0;
        
        visitedNode.add(curRow + "," + curCol);
        
        for(int dir: arrows){
            for(int i = 0; i<2; i++){
                int nextRow = curRow + dRow[dir];
                int nextCol = curCol + dCol[dir];
                
                String nextNode = nextRow + "," + nextCol;
                String edge = curRow + "," + curCol + "->" + nextRow + "," + nextCol;
                String reverseEdge = nextRow + "," + nextCol + "->" + curRow + "," + curCol;
                
                if(visitedNode.contains(nextNode) && !visitedEdge.contains(edge)){
                    answer++;
                }
                
                visitedNode.add(nextNode);
                visitedEdge.add(edge);
                visitedEdge.add(reverseEdge);
                
                curRow = nextRow;
                curCol = nextCol;
            }
        }
        
        return answer;
    }
    
}