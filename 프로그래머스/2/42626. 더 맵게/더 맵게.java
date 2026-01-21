import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int sc:scoville){
            pq.add(sc);
        }
        boolean possible = true;
        int cnt = 0;
        while(true){
            if(pq.isEmpty()){
                possible = false;
                break;
            }
            int cur = pq.poll();
            if(cur >= K) break;
            if(pq.isEmpty()){
                possible = false;
                break;
            }
            int next = pq.poll();
            int mix = cur + next*2;
            pq.add(mix);
            cnt++;
        }
        if(!possible){
            return -1;
        }
        return cnt;

    }
}