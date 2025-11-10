import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        HashMap<String,Integer> map = new HashMap<>();
        for(int i = 0; i<participant.length; i++){
            map.put(participant[i], map.getOrDefault(participant[i],0) + 1);
        }
        for(int i = 0; i<completion.length; i++){
            if(map.get(completion[i]) == 1){
                map.remove(completion[i]);
            } else {
                map.replace(completion[i], map.get(completion[i]) - 1);
            }
        }
        String answer = map.keySet().iterator().next();
        return answer;
    }
}