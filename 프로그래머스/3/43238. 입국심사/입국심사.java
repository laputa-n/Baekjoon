class Solution {
    //심사 시간 최솟값
    public long solution(int n, int[] times){
        //n: 대기 인원
        //times: 각 심사시간
        
        long start = 1;
        long end = (long)1000000000*n;
        while(start<end){
            long mid = (start+end)/2;
            
            //심사시간 줄이면 사람 수 적어지고,
            //심사시간 늘리면 사람 수 많아지고
            long cnt = peoples(times, mid);
            
            if(cnt < n){
                //심사시간 늘려야 하는 경우는 사람 수가 적은 경우
                start = mid + 1;
            } else {
                end = mid;
            }
            
        }
        
        return end;
    }
    
    //총 심사시간이 time일 때, 심사를 받을 수 있는 사람의 수
    static long peoples(int[] times, long time){
        long cnt = 0;
        for(int t: times){
            cnt += time/t;
        }
        
        return cnt;
    }
}