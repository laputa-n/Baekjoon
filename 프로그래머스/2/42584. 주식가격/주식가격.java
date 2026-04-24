import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int size = prices.length;
        
        int[] ans = new int[size];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        
        for(int i = 0; i<size; i++){
            while(!stack.isEmpty() && prices[i] < prices[stack.peek()]){
                ans[stack.peek()] = i-stack.pop();
            }
            stack.push(i);
        }
        
        while(!stack.isEmpty()){
            ans[stack.peek()] = size - 1 - stack.pop();
        }
        
        return ans;
    }
}