import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        
        for(String operation: operations){
            char cmd = operation.charAt(0);
            int num = Integer.parseInt(operation.split(" ")[1]);
            
            if(cmd == 'I'){
                minHeap.add(num);
                maxHeap.add(num);
            } else {
                if(maxHeap.isEmpty()) continue;
                
                if(num == 1){
                    int max = maxHeap.poll();
                    minHeap.remove(Integer.valueOf(max));
                } else {
                    int min = minHeap.poll();
                    maxHeap.remove(Integer.valueOf(min));
                }
            }
        }
        
        int[] ans = new int[2];
        if(maxHeap.isEmpty()) return ans;
        
        ans[0] = maxHeap.poll();
        ans[1] = minHeap.poll();
        
        return ans;
    }
}