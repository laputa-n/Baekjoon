import java.util.*;
class Solution {
    static int ans = Integer.MAX_VALUE;
    static boolean[] visited;
    public int solution(String begin, String target, String[] words) {
        visited = new boolean[words.length];
        DFS(begin, target, 0, words);
        return ans == Integer.MAX_VALUE? 0 : ans;
    }
    static void DFS(String cur, String tar, int cnt, String[] words){
        if(cur.equals(tar)){
            ans = Math.min(ans,cnt);
            return;
        }
        int size = words.length;
        for(int i = 0; i<size; i++){
            if(diff(cur, words[i]) == 1 && !visited[i]){
                visited[i] = true;
                DFS(words[i], tar, cnt+1, words);
                visited[i] = false;
            }
        }
    }
    static int diff(String a, String b){
        int size = a.length();
        int cnt = 0;
        for(int i = 0; i<size; i++){
            if(a.charAt(i) != b.charAt(i)) cnt++;
        }
        return cnt;
    }
}