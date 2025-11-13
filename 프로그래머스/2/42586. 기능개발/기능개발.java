import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int length = progresses.length;
        int checkIndex = 0;
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        while(true){
            for(int i = 0; i < length; i++){
                if(progresses[i] > 100)
                    continue;
                progresses[i] += speeds[i];
            }
            if(progresses[checkIndex] >= 100){
                int cnt = 0;
                int i = checkIndex;
                while(i<length && progresses[i] >= 100){
                    cnt++;
                    i++;
                }
                checkIndex += cnt;
                deque.addLast(cnt);
            }
            if(checkIndex >= length)
                break;
        }
        return deque.stream().mapToInt(i->i).toArray();
    }
}