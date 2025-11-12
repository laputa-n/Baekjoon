import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String[] cloth : clothes) {
            map.put(cloth[1], map.getOrDefault(cloth[1],0) + 1);
        }
        int ans = 1;
        for(String key: map.keySet()) {
            ans *= (map.get(key)+1);
        }

        return ans - 1;
    }
}