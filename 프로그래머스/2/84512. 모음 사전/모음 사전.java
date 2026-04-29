import java.util.*;
class Solution {
    static ArrayList<String> dic = new ArrayList<>();
    public int solution(String word) {
        String alphabet = "AEIOU";
        DFS("", alphabet);
        return dic.indexOf(word)+1;
    }
    
    static void DFS(String s, String alphabet){
        if(s.length() == 5) return;
        for(int i = 0; i<5; i++){
            String temp = s + alphabet.charAt(i);
            dic.add(temp);
            DFS(temp, alphabet);
        }
    }
}