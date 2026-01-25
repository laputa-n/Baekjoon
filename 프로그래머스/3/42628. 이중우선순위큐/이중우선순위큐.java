import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        TreeMap<Integer,Integer> map = new TreeMap<>();
        String[] oper;
        for(String operation:operations){
            oper = operation.split(" ");
            if(oper[0].equals("I")){
                int num = Integer.parseInt(oper[1]);
                map.put(num,map.getOrDefault(num,0) + 1);
            } else {
                if(map.isEmpty()) continue;
                int key = oper[1].equals("1")?map.lastKey():map.firstKey();
                int cnt = map.get(key);
                if(cnt == 1){
                    map.remove(key);
                } else {
                    map.put(key,cnt-1);
                }
            }
        }

        if(map.isEmpty()) return new int[]{0,0};
        return new int[]{map.lastKey(),map.firstKey()};
    }
}