import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        
        ArrayList<Integer> [] prevList = new ArrayList[n+1];
        ArrayList<Integer> [] nextList = new ArrayList[n+1];
        for(int i = 1; i<=n; i++){
            prevList[i] = new ArrayList<>();
            nextList[i] = new ArrayList<>();
        }
        
        for(int[] r: results){
            int win = r[0];
            int lose = r[1];
            
            nextList[win].add(lose);
            prevList[lose].add(win);
        }
        
        int cnt = 0;
        
        for(int i = 1; i<=n; i++){
            boolean[] checked = new boolean[n+1];
            Queue<Integer> q = new LinkedList<>();
            int prevCnt = prevList[i].size();
            
            for(int p: prevList[i]){
                q.add(p);
                checked[p] = true;
            }
            
            while(!q.isEmpty()){
                int prevNum = q.poll();
                for(int pp: prevList[prevNum]){
                    if(!checked[pp]){
                        q.add(pp);
                        prevCnt++;
                        checked[pp] = true;
                    }
                }
            }
            
            checked = new boolean[n+1];
            q = new LinkedList<>();
            int nextCnt = nextList[i].size();
            
            for(int nn: nextList[i]){
                q.add(nn);
                checked[nn] = true;
            }
            
            while(!q.isEmpty()){
                int nextNum = q.poll();
                for(int nn: nextList[nextNum]){
                    if(!checked[nn]){
                        q.add(nn);
                        nextCnt++;
                        checked[nn] = true;
                    }
                }
            }
            
            if(prevCnt + nextCnt == n-1) cnt++;
        }
        
        return cnt;
    }
}