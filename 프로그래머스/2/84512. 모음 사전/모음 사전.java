import java.util.*;
class Solution {
    public int solution(String word) {
        String std = "AEIOU";
        List<String> words = new ArrayList<>();
        DFS(std,"",words);
        return words.indexOf(word)+1;
    }
    static void DFS(String std,String cur, List<String> words){
        if(cur.length() == 5) return;
        for(int i = 0 ; i<5; i++){
            words.add(cur+std.charAt(i));
            DFS(std,cur+std.charAt(i),words);
        }
    }
}