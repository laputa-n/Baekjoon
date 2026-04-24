import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int size = speeds.length;
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        
        for(int i = 0; i<size; i++){
            int todo = 100 - progresses[i];
            if(todo%speeds[i] == 0) queue.add(todo/speeds[i]);
            else queue.add(todo/speeds[i]+1);
        }
        
        List<Integer> ans = new ArrayList<>();
        while(!queue.isEmpty()){
            int day = queue.poll();
            int cnt = 0;
            while(!queue.isEmpty()&&day >= queue.peek()){
                queue.poll();
                cnt++;
            }
            ans.add(cnt+1);
        }
        
        return ans.stream().mapToInt(i->i).toArray();
    }
}