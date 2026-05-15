import java.util.*;
class Solution {
    static int minCnt;
    static boolean[] checked;
    public int solution(String begin, String target, String[] words) {
        
        boolean isIn = false;
        
        for(String word: words){
            if(word.equals(target)){
                isIn = true;
                break;
            }
        }
        
        if(!isIn) return 0;
        
        minCnt = 51;
        checked = new boolean[words.length];
        
        DFS(begin, target, words, 0);
        
        return minCnt == 51? 0: minCnt;
    }
    
    static void DFS(String cur, String tar, String[] words, int changed){
        if(cur.equals(tar)){
            minCnt = Math.min(changed, minCnt);
            return;
        }
        
        
        for(int i = 0; i<words.length; i++){
            if(calDiff(cur, words[i]) == 1 && !checked[i]){
                checked[i] = true;
                DFS(words[i], tar, words, changed+1);
                checked[i] = false;
            }
        }
    }
    
    static int calDiff(String a, String b){
        int cnt = 0;
        
        for(int i = 0; i<a.length(); i++){
            if(a.charAt(i) != b.charAt(i)) cnt ++;
        }
        
        return cnt;
    }
}