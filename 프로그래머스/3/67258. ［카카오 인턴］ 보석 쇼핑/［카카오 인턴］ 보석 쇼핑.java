import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] ans = new int[2];
        
        HashSet<String> names = new HashSet<>();
        for(String name:gems){
            names.add(name);
        }
        
        HashMap<String,Integer> map = new HashMap<>();
        int kind = names.size();
        
        int len = Integer.MAX_VALUE;
        int start = 0;
        for(int end = 0; end<gems.length; end++){
            map.put(gems[end], map.getOrDefault(gems[end],0) + 1);
            
            while(1<map.get(gems[start])){
                map.put(gems[start], map.get(gems[start]) - 1);
                start++;
            }
            
            if(kind == map.size() && len > end-start+1){
                len = end-start+1;
                ans[0] = start+1;
                ans[1] = end+1;
            }
        }
        
        return ans;
    }
}