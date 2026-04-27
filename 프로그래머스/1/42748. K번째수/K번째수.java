import java.util.*;
class Solution {
    public int[] solution(int[] array, int[][] commands) {
        
        int size = commands.length;
        int[] ans = new int[size];
        
        //각 커맨드 실행
        for(int a = 0; a<size; a++){
            int i = commands[a][0];
            int j = commands[a][1];
            int k = commands[a][2];
            //배열 자르기
            int[] tmp = new int[j-i+1];
            for(int b = i; b<=j; b++){
                tmp[b-i] = array[b-1];
            }
            //배열 정렬
            Arrays.sort(tmp);
            
            ans[a] = tmp[k-1];
        }
        
        return ans;
    }
}