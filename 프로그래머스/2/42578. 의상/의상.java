import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        HashMap<String, Integer> map = new HashMap<>();
        for(String[] tar:clothes){
            String name = tar[0];
            String type = tar[1];
            map.put(type,map.getOrDefault(type,0)+1);
        }
        int cnt = 1;
        for(String key:map.keySet()){
            cnt *= (map.get(key)+1);
        }
        return cnt-1;
    }
}