import java.util.*;
class Solution {
    static Set<Integer> set = new HashSet<>();
    static boolean[] checked;
    static int max = 0;
    static boolean[] isPrime;
    public int solution(String numbers) {
        checked = new boolean[numbers.length()];
        for(int i = 0; i<numbers.length(); i++){
            DFS(i,"",numbers,0);
        }
        
        isPrime = new boolean[max+1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for(int i = 2; i*i<=max; i++){
            if(isPrime[i]){
                for(int j = i*i; j<=max; j+=i){
                    isPrime[j] = false;
                }
            }
        }
        
        int cnt = 0;
        for(int i:set){
            if(isPrime[i]) cnt++;
        }
        
        return cnt;
        
    }
    
    static void DFS(int target, String s, String numbers, int cnt){
        checked[target] = true;
        String added = s + String.valueOf(numbers.charAt(target));
        
        set.add(Integer.parseInt(added));
        max = Math.max(max, Integer.parseInt(added));
        
        if(cnt + 1 == numbers.length()){
            checked[target] = false;
            return;
        }
        
        for(int i = 0; i<numbers.length(); i++){
            if(!checked[i]){
                DFS(i,added, numbers, cnt+1);
            }
        }
        
        checked[target] = false;
    }
}