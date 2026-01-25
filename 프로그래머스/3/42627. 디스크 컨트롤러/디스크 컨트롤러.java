import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (a,b) -> Integer.compare(a[0],b[0]));

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->Integer.compare(a[1],b[1]));

        int n = jobs.length;
        int idx = 0;
        int time = 0;
        long sum = 0;
        int done = 0;
        while(done<n){
            while(idx < n && jobs[idx][0] <= time){
                pq.offer(jobs[idx]);
                idx++;
            }

            if(pq.isEmpty()){
                time = jobs[idx][0];
                continue;
            }

            int[] cur = pq.poll();
            time += cur[1];
            sum +=  time - cur[0];
            done++;
        }

        return (int)sum/n;
    }
}