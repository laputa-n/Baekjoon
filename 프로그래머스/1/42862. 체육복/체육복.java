import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        
        Arrays.sort(lost);
        Arrays.sort(reserve);
        
        //여분 가져왔고, 잃어버린 사람 처리
        for(int i = 0; i<lost.length; i++){
            for(int j = 0; j<reserve.length; j++){
                if(lost[i] == reserve[j]){
                    lost[i] = -1;
                    reserve[j] = -1;
                }
            }
        }
        
        for(int i = 0; i<lost.length; i++){
            if(lost[i] == -1) continue;
            for(int j = 0; j<reserve.length; j++){
                if(reserve[j] == lost[i] - 1 || reserve[j] == lost[i] + 1){
                    lost[i] = -1;
                    reserve[j] = -1;
                    break;
                } 
            }
        }
        
        int cnt = 0;
        for(int l:lost){
            if(l!=-1) cnt++;
        }
        
        return n-cnt;
        
        
        
    }
}