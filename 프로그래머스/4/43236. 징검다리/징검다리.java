import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        //지점간 최소 거리가 minDis일 때, 바위 제거 개수 removeCnt
        //minDis 증가 -> removeCnt 증가
        //removeCnt가 n보다 크면 minDis는 줄여야 함
        //upperBound - 1 이 답
        //최소거리 minDis일 때, 제거해야하는 바위 개수 새는 메소드 필요
        Arrays.sort(rocks);
        int start = 1;
        int end = distance+1;
        while(start<end){
            int mid = (start+end) / 2;
            
            int removeCnt = removeRocks(mid,rocks,distance);;
            
            if(removeCnt<=n){
                //minDis 증가시킨다는 것은 removeCnt를 증가시키는 것, 즉 removeCnt가 n보다 작다는 뜻
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        
        return end-1;
        
    }
    
    static int removeRocks(int minDis, int[] rocks, int distance){
        int point = 0;
        int cnt = 0;
        for(int i = 0; i<rocks.length; i++){
            if(rocks[i] - point < minDis){
                cnt++;
            } else {
                point = rocks[i];
            }
        }
        
        if(distance-point < minDis)
            cnt++;
        
        return cnt;
        
    }
}