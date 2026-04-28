import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] cnt = new int[3];
        
        for(int i = 0; i<answers.length; i++){
            if(solve1(i) == answers[i]) cnt[0]++;
            if(solve2(i) == answers[i]) cnt[1]++;
            if(solve3(i) == answers[i]) cnt[2]++;
        }
        
        int max = 0;
        for(int c: cnt) max = Math.max(max,c);
        
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i<3; i++){
            if(max == cnt[i]) result.add(i+1);
        }
        
        return result.stream().mapToInt(i->i).toArray();
    }
    
    static int solve1(int i){
        return i%5 + 1;
    }
    
    static int solve2(int i){
        if(i%2 == 0) return 2;
        
        int t = i%8;
        if(t == 1) return 1;
        
        return 3 + (t-3)/2;
    }
    
    static int solve3(int i){
        return switch(i % 10){
            case 0, 1 -> 3;
            case 2, 3 -> 1;
            case 4, 5 -> 2;
            case 6, 7 -> 4;
            case 8, 9 -> 5;
          default -> -1;
        };
    }
}
