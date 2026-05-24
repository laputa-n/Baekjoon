import java.util.*;

class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        //key: M x M, lock: N x N, M<=N
        int M = key.length;
        int N = lock.length;
        
        int len = N + 2*M - 2;
        int[][] map = new int[len][len];
        int offset = M-1;
        
        for(int i = 0; i<N; i++){
            for(int j =0; j<N; j++){
                map[i+offset][j+offset] = lock[i][j];
            }
        }
        
        for(int i = 0; i<4; i++){
            if(check(map, key))
                return true;
            if(i == 3) continue;
            key = rotate(key);
        }
        
        return  false;
    }
    static boolean check(int[][] map, int[][] key){
        int M = key.length;
        int N = map.length;
        
        int lockLen = N - 2*M + 2;
        int offset = M - 1;
        
        for(int i = 0; i <= N-M; i++){
            for(int j = 0; j<= N-M; j++){
                
                for(int k = 0; k<M; k++){
                    for(int l = 0; l<M; l++){
                        map[i+k][l+j] += key[k][l];
                    }
                }
                
                boolean flag = true;
                for(int k = 0; k<lockLen; k++){
                    if(!flag) break;
                    for(int l = 0; l<lockLen; l++){
                        if(map[k+offset][l+offset] != 1){
                            flag = false;
                            break;
                        }
                    }
                }
                
                if(flag) return true;
                
                for(int k = 0; k<M; k++){
                    for(int l = 0; l<M; l++){
                        map[i+k][l+j] -= key[k][l];
                    }
                }
            }
        }
        
        return false;
    }
    static int[][] rotate(int[][] key){
        int M = key.length;
        
        int[][] tmp = new int[M][M];
        for(int i = 0; i<M; i++){
            for(int j = 0; j<M; j++){
                tmp[i][j] = key[M-1-j][i];            }
        }
        
        return tmp;
    }
}