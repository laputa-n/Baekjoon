import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(String s: operations){
            String[] operation = s.split(" ");
            String cmd = operation[0];
            String number = operation[1];
            
            if(cmd.equals("I")){
                pq.add(Integer.parseInt(number));
            } else {
                if(pq.isEmpty()) continue;
                
                if(number.equals("1")){
                    PriorityQueue<Integer> pq2 = new PriorityQueue<>((o1,o2) -> o2 - o1);
                    
                    while(!pq.isEmpty()){
                        pq2.add(pq.poll());
                    }
                    
                    pq2.poll();
                    
                    while(!pq2.isEmpty()){
                        pq.add(pq2.poll());
                    }
                    
                } else {
                    pq.poll();
                }
            }
        }
        
        int[] ans = new int[2];
        if(pq.isEmpty()){
            ans[0] = 0;
            ans[1] = 0;
        } else {
            ans[1] = pq.peek();
            PriorityQueue<Integer> pq2 = new PriorityQueue<>((o1,o2) -> o2-o1);
            while(!pq.isEmpty()){
                pq2.add(pq.poll());
            }
            ans[0] = pq2.peek();
        }
        
        return ans;
    }
}