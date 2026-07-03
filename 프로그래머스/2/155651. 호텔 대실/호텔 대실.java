import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        PriorityQueue<int[]> book_time2 
            = new PriorityQueue<>((t1,t2) -> Integer.compare(t1[0],t2[0]));
        int size = book_time.length;
        for(int i = 0; i<size; i++){
            book_time2.add(new int[]{transToMinute(book_time[i][0])
                , transToMinute(book_time[i][1])});
        }
        
        int ans = 1;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(book_time2.poll()[1]);
        int cnt = 1;
        
        while(!book_time2.isEmpty()){
            int[] t2 = book_time2.poll();
            int t = pq.poll();
            
            if(t+10 > t2[0]){
                cnt++;
                pq.add(t);
            }
            pq.add(t2[1]);
            ans = Math.max(ans,cnt);
        }
        
        return ans;
    }
    static int transToMinute(String time){
        String[] HHMM = time.split(":");
        int HH = Integer.parseInt(HHMM[0]);
        int MM = Integer.parseInt(HHMM[1]);
        
        return 60*HH + MM;
    }
}