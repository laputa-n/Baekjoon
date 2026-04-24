import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for(int t: arr){
            if(!q.isEmpty() && q.getLast() == t) continue;
            q.offer(t);
        }
        return q.stream().mapToInt(i->i).toArray();
    }
}