import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int ans = 0;
        ArrayDeque<Process> queue = new ArrayDeque<>();
        for (int i = 0; i < priorities.length; i++) {
            queue.add(new Process(i, priorities[i]));
        }
        while(true){
            ArrayDeque<Process> temp = new ArrayDeque<>();
            Process p = queue.poll();
            boolean flag = false;
            while(!queue.isEmpty()){
                Process p1 = queue.poll();
                if(p.priority<p1.priority){
                    flag = true;
                }
                temp.offer(p1);
            }
            if(flag){
                temp.offer(p);
            } else {
                ans++;
                if(p.idx == location)
                    return ans;
            }
            queue= temp;
        }
    }
    static class Process{
        int idx, priority;
        public Process(int idx, int priority) {
            this.idx = idx;
            this.priority = priority;
        }
    }
}