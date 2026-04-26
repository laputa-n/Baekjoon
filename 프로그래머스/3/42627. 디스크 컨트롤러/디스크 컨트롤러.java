import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (j1,j2) -> j1[0] - j2[0]);
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((j1,j2) -> j1[1] - j2[1]);
        int time = 0;
        int idx = 0;
        int total = 0;
        int cnt = 0;
        
        while(cnt < jobs.length){
            
            while(idx < jobs.length && jobs[idx][0] <= time){
                pq.add(jobs[idx++]);
            }
            
            if(pq.isEmpty()){
                time = jobs[idx][0];
            } else {
                int[] job = pq.poll();
                time += job[1];
                total += (time - job[0]);
                cnt++;
            }
        }
        
        return total/jobs.length;
    }
}