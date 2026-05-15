import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int time: works){
            pq.add(time);
        }
        
        while(n-->0 && !pq.isEmpty()){
            int tar = pq.poll();
            tar--;
            if(tar > 0){
                pq.add(tar);
            }
        }
        
        long sum = 0;
        while(!pq.isEmpty()){
            sum += (long) Math.pow(pq.poll(), 2);
        }
        
        return sum;
    }
}