import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int p:priorities){
            pq.add(p);
        }
        int ans = 0;
        while(!pq.isEmpty()){
            for(int i = 0; i<priorities.length; i++){
                if(pq.peek() == priorities[i]){
                    pq.poll();
                    ans++;
                    if(i == location){
                        return ans;
                    }
                }
            }
        }
        
        return ans;
    }
    
}