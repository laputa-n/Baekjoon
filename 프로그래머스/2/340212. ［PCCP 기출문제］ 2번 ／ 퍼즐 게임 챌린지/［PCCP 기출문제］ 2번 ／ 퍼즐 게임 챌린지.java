class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int lo = 1;
        int hi = 100000;
        while(lo<hi){
            int mid = (lo+hi)/2;
            long t = times[0];
            for(int i = 1; i<diffs.length; i++){
                if(t >= limit) break;
                if(diffs[i] <= mid){
                    t+= times[i];
                } else {
                    t += ((long) (times[i] + times[i - 1]) * (diffs[i] - mid) + times[i]);
                }
            }
            if(t <= limit){
                hi = mid;
            }else{
                lo  = mid + 1;
            }
        }
        return hi;
    }
}