import java.util.*;
class Solution {
    static boolean[] used;
    static ArrayList<String> ans = new ArrayList<>();
    static String[] solution(String[][] tickets) {
        used = new boolean[tickets.length];
        Arrays.sort(tickets, (a,b) -> {
            if(a[0].equals(b[0])) return a[1].compareTo(b[1]);
            return a[0].compareTo(b[0]);
        });
        DFS(0,"ICN",tickets, "ICN");
        return ans.get(0).split(" ");
    }
    static void DFS(int cnt, String city, String[][] tickets, String path){
        if(cnt == tickets.length){
            ans.add(path);
            return;
        }

        for(int i = 0; i<tickets.length; i++){
            if(tickets[i][0].equals(city) && !used[i]){
                used[i] = true;
                DFS(cnt+1, tickets[i][1], tickets, path+" " +tickets[i][1]);
                used[i] = false;
            }
        }
    }
}