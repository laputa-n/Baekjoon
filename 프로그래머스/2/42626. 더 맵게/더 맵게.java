import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int s: scoville){
            pq.add(s);
        }
        int cnt = 0;
        boolean flag = false;
        while(true){
            if(pq.peek() >= K){
                flag = true;
                break;
            }
            
            if(pq.size() < 2) break;
            
            int x = pq.poll();
            int y = pq.poll();
            pq.add(x + y*2);
            cnt++;
        }
        
        return flag?cnt:-1;

    }
}