class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int passed = 0;
        int nextNum = 0;
        int[] bridge = new int[bridge_length];
        int time = 0;
        while(passed<truck_weights.length){
            int weightsOnBridge = 0;
            for(int i = bridge.length-1; i>=0; i--){
                if(i == bridge_length - 1){
                    if(bridge[i] != 0){
                        bridge[i] = 0;
                        passed++;
                    }
                } else {
                    bridge[i+1] = bridge[i];
                    bridge[i] = 0;
                    weightsOnBridge += bridge[i+1];
                }
            }
            if(!(nextNum>=truck_weights.length)){
                if(weight - weightsOnBridge >= truck_weights[nextNum]){
                    bridge[0] = truck_weights[nextNum++];
                }
            }
            time++;
        }
        return time;
    }
}