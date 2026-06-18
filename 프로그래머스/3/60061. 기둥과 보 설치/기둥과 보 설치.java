import java.util.*;

class Solution {
    static int len;
    static boolean[][] pillar;
    static boolean[][] beam;
    public int[][] solution(int n, int[][] build_frame) {
        //0: x의 좌표
        //1: y의 좌표
        //2: 구조물 종류(0 - 기둥 / 1 - 보)
        //3: 설치 or 삭제(0 - 삭제 / 1 - 설치)
        len = n;
        pillar = new boolean[n+1][n+1];
        beam = new boolean[n+1][n+1];
        
        for(int[] cmd: build_frame){
            int x = cmd[0];
            int y = cmd[1];
            int type = cmd[2];
            int cmdType = cmd[3];
            
            //설치
            if(cmdType == 1){
                if(canInstall(x,y,type)) install(x,y,type);
            //삭제
            } else {
                uninstall(x,y,type);
                if(!isValidStructure()) install(x,y,type);
            }
        }
        
        List<int[]> answerList = new ArrayList<>();
        for(int i = 0; i<= len; i++){
            for(int j = 0; j<=len; j++){
                if(pillar[i][j]) answerList.add(new int[]{i,j,0});
                if(beam[i][j]) answerList.add(new int[]{i,j,1});
            }
        }
        
        answerList.sort((i1,i2) -> {
            if(i1[0] == i2[0] && i1[1] == i2[1]) return i1[2] - i2[2];
            if(i1[0] == i2[0]) return i1[1] - i2[1];
            return i1[0] - i2[0];
        });
        
        return answerList.toArray(new int[answerList.size()][3]);
    }
    static boolean isValidStructure(){
        for(int i = 0; i<=len; i++){
            for(int j = 0; j<=len; j++){
                if(pillar[i][j] && !canInstall(i,j,0)) return false;
                if(beam[i][j] && !canInstall(i,j,1)) return false;
            }
        }
        
        return true;
    }
    static boolean canInstall(int x, int y, int type){
        //기둥
        if(type == 0){
            if(y == 0) return true;
            if(hasPillar(x, y-1)) return  true;
            if(hasBeam(x-1, y) || hasBeam(x, y)) return true;
        //보
        } else {
            if (hasPillar(x, y - 1) || hasPillar(x + 1, y - 1)) return true;
            if (hasBeam(x - 1, y) && hasBeam(x + 1, y)) return true;
        }
        return false;
    }
    static void uninstall(int x, int y, int type){
        if(type == 0){
            pillar[x][y] = false;
        } else {
            beam[x][y] = false;
        }
    }
    static void install(int x, int y, int type){
        if(type == 0){
            pillar[x][y] = true;
        } else {
            beam[x][y] = true;
        }
    }
    static boolean hasPillar(int x, int y) {
    if (x < 0 || x > len || y < 0 || y > len) return false;
    return pillar[x][y];
}

static boolean hasBeam(int x, int y) {
    if (x < 0 || x > len || y < 0 || y > len) return false;
    return beam[x][y];
}
}