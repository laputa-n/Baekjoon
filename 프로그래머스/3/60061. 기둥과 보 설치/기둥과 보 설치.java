import java.util.*;

class Solution {
    static List<Character>[][] matrix;
    static int len;
    public int[][] solution(int n, int[][] build_frame) {
        // 기둥 1) 바닥 위 2) 보의 한쪽 끝 부분 위 3) 다른 기둥 위
        // 보 1) 한쪽 끝 부분이 기둥 위 2) 양쪽 끝 부분이 다른 보와 연결
        matrix = new ArrayList[n+1][n+1];
        for(int i = 0; i<=n; i++){
            for(int j = 0; j<=n; j++){
                matrix[i][j] = new ArrayList<>();
            }
        }
        len = n;
        
        for(int[] task: build_frame){
            int x = task[0];
            int y = task[1];
            int a = task[2]; //0: 기둥 I Post / 1: 보 - Pillar
            int b = task[3]; //0: 삭제 / 1: 설치
            
            int[] rc = xyTorc(new int[]{x,y}, n);
            int row = rc[0];
            int col = rc[1];
            
            //기둥
            if(a == 0){
                //설치
                if(b == 1){
                    if(canBuildPostAtThis(row, col)){
                        matrix[row][col].add('G');
                    }
                }
                //삭제
                else {
                    if(canDeletePostAtThis(row, col)){
                        matrix[row][col].remove(Character.valueOf('G'));
                    }
                }
            }
            //보
            else {
                //설치
                if(b == 1){
                    if(canBuildPillarAtThis(row, col)){
                        matrix[row][col].add('B');
                    }
                } 
                //삭제
                else {
                    if(canDeletePillarAtThis(row, col)){
                        matrix[row][col].remove(Character.valueOf('B'));
                    }
                }
            }
        }
        
        
        List<int[]> result = new ArrayList<>();
        
        for(int i = 0; i<=n; i++){
            for(int j = n; j>=0; j--){
                if(matrix[i][j].contains('G')){
                    int[] xy = rcToxy(new int[]{i,j}, n);
                    result.add(new int[]{xy[0], xy[1], 0});
                }
                
                if(matrix[i][j].contains('B')){
                    int[] xy = rcToxy(new int[]{i,j}, n);
                    result.add(new int[]{xy[0], xy[1], 1});
                }
            }
        }
        
        int[][] answer = new int[result.size()][3];
        for(int i = 0; i<result.size(); i++){
            answer[i]  = result.get(i);
        }

        Arrays.sort(answer, (p1, p2) -> {
            if(p1[0] != p2[0]) return p1[0] - p2[0];      // x 오름차순
            if(p1[1] != p2[1]) return p1[1] - p2[1];      // y 오름차순
            return p1[2] - p2[2];                          // 기둥(0)이 보(1)보다 먼저
        });

        return answer;
    }
    // 기둥 1) 바닥 위 2) 보의 한쪽 끝 부분 위 3) 다른 기둥 위
    // 보 1) 한쪽 끝 부분이 기둥 위 2) 양쪽 끝 부분이 다른 보와 연결
    private boolean canBuildPostAtThis(int r, int c){
    if(r == len) return true;
    if(matrix[r+1][c].contains('G')) return true;
    if(matrix[r][c].contains('B') || (c > 0 && matrix[r][c-1].contains('B'))) return true;
    return false;
}

private boolean canBuildPillarAtThis(int r, int c){
    if(matrix[r+1][c].contains('G') || matrix[r+1][c+1].contains('G')) return true;
    if(c > 0 && c < len && matrix[r][c-1].contains('B') && matrix[r][c+1].contains('B')) return true;
    return false;
}
    
    private boolean canDeletePostAtThis(int r, int c){
        matrix[r][c].remove(Character.valueOf('G'));
        boolean ok = isStructureValid();
        matrix[r][c].add('G');
        return ok;
    }

    private boolean canDeletePillarAtThis(int r, int c){
        matrix[r][c].remove(Character.valueOf('B'));
        boolean ok = isStructureValid();
        matrix[r][c].add('B');
        return ok;
    }
    
    private int[] xyTorc (int[] xy, int n){
        int x = xy[0];
        int y = xy[1];
        
        int col = x;
        int row = n - y;
        
        return new int[]{row, col};
    }
    
    private int[] rcToxy(int[] rc, int n){
        int row = rc[0];
        int col = rc[1];
        
        int x = col;
        int y = n - row;
        
        return new int[]{x,y};
    }
    
    private boolean isStructureValid(){
    for(int r = 0; r <= len; r++){
        for(int c = 0; c <= len; c++){
            if(matrix[r][c].contains('G') && !canBuildPostAtThis(r, c)) return false;
            if(matrix[r][c].contains('B') && !canBuildPillarAtThis(r, c)) return false;
        }
    }
    return true;
}
}