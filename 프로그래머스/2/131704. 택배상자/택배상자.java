import java.util.*;
class Solution {
    public int solution(int[] order) {
        int size = order.length;
        
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        
        for(int i = 1; i<=size; i++){
            queue.offer(i);
        }
        
        int cnt = 0;
        for(int target: order){
            if(!stack.isEmpty() && stack.peekFirst() == target){
                stack.pop();
                cnt++;
                continue;
            }
            
            if(!queue.isEmpty() && queue.peekFirst() == target){
                queue.poll();
                cnt++;
                continue;
            }
            
            while(!queue.isEmpty() && queue.peekFirst() != target){
                stack.push(queue.poll());
            }
            
            if(!queue.isEmpty() && queue.peekFirst() == target){
                queue.poll();
                cnt++;
                continue;
            } else if(!stack.isEmpty() && stack.peekFirst() == target){
                stack.pop();
                cnt++;
                continue;
            }else {
                break;
            }
        }
        
        return cnt;
    }
}