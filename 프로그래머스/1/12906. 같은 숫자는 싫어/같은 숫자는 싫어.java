import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int j : arr) {
            if (deque.isEmpty()) {
                deque.push(j);
                continue;
            }
            if (deque.peekLast() != j) {
                deque.addLast(j);
            }
        }
        return deque.stream().mapToInt(i -> i).toArray();
    }
}