import java.util.*;
class Solution {
    static HashSet<HashSet<String>> ans = new HashSet<>();
    public int solution(String[] user_id, String[] banned_id) {
        DFS(new HashSet<>(), 0, user_id, banned_id);
        
        return ans.size();
    }
    static void DFS(HashSet<String> ids, int depth, String[] user_id, String[] banned_id){
        if(depth == banned_id.length){
            ans.add(ids);
            return;
        }
        
        for(int i = 0; i<user_id.length; i++){
            if(ids.contains(user_id[i])) continue;
            if(isInvolved(banned_id[depth], user_id[i])){
                ids.add(user_id[i]);
                DFS(new HashSet(ids),depth+1, user_id, banned_id);
                ids.remove(user_id[i]);
            }
        }
    }
    static boolean isInvolved(String ban, String user){
        if(ban.length() != user.length()) return false;
        
        for(int i = 0; i<ban.length(); i++){
            if(ban.charAt(i) == '*') continue;
            
            if(ban.charAt(i) != user.charAt(i)) return false;
        }
        
        return true;
    }
}