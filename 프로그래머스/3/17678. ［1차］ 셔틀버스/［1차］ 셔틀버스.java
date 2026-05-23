import java.util.*;
class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        //셔틀 09:00부터 n회 t분 간격으로 역에 도착, 셔틀 최대 인원 수 m
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        
        for(String time: timetable){
            String[] s = time.split(":");
            int hh = Integer.parseInt(s[0]);
            int mm = Integer.parseInt(s[1]);
            
            pq.add(hh*60 + mm);
        }
        
        int now = 540;
        while(n-->0){
            int cnt = 0;
            map.put(now, new ArrayList<Integer>());
            while(cnt < m && !pq.isEmpty()){
                int p = pq.peek();
                
                if(p > now)
                    break;
                
                map.get(now).add(pq.poll());
                cnt++;
            }
            now += t;
        }
        
        now -= t;
        List<Integer> target = map.get(now);
        int ans = 0;
        if(target.size() <= m-1){
            ans = now;
        } else {
            ans = target.get(target.size()-1) - 1;
        }
        
        
        return String.format("%02d:%02d", ans/60, ans%60);
        
    }
}