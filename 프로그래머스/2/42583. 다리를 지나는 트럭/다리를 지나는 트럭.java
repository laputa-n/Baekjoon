import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        //0: 시간, 1: 트럭 번호
        ArrayDeque<int[]> bridge = new ArrayDeque<>();
        
        //time: 현재 시각, next: 다음 트럭 번호, total_weights: 다리 위 무게 총합
        int time = 1;
        int next = 1;
        int total_weights = 0;
        
        bridge.offer(new int[]{1,0});
        total_weights += truck_weights[0];
        
        while(!bridge.isEmpty()){
            time++;
            //맨 앞
            int[] head = bridge.peek();
            //1. 현재 시간 기준 다 건넜으면, 다리 위에서 제거
            if(time - head[0] >= bridge_length){
                bridge.poll();
                total_weights -= truck_weights[head[1]];
            }
            
            //2. 다음 차가 다리 위 진입할 수 있으면, 넣어주기
            if(next >= truck_weights.length) continue;
            
            if(total_weights + truck_weights[next] <= weight){
                bridge.offer(new int[]{time, next});
                total_weights += truck_weights[next];
                next++;
            }
        }
        
        return time;
    }        
}
