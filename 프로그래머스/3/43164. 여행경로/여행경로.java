import java.util.*;
class Solution {
    static boolean[] visited;
    static String ans;
    static boolean flag = false;
    static String[] solution(String[][] tickets) {
        Arrays.sort(tickets, (t1,t2) -> t1[1].compareTo(t2[1]));
        visited = new boolean[tickets.length];
        DFS("ICN", "ICN", tickets, 0);
        ans = ans.trim();
        return ans.split(" ");
        
    }
    static void DFS(String cur, String path, String[][] tickets, int depth){
        if(flag) return;
        if(depth == tickets.length){
            ans = path;
            flag = true;
            return;
        }
        for(int i = 0; i<tickets.length; i++){
            if(tickets[i][0].equals(cur)){
                if(!visited[i]){
                    visited[i] = true;
                    DFS(tickets[i][1], path + " " + tickets[i][1], tickets, depth+1);
                    visited[i] = false;
                }
            }
        }
    }
}