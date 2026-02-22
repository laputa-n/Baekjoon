import java.util.*;
class Solution {
    static Set<Integer> set = new HashSet<>();
    static boolean[] checked;
    static int max = Integer.MIN_VALUE;
    public int solution(String numbers) {
        int answer = 0;
        checked = new boolean[numbers.length()];
        DFS("0",numbers);
        boolean[] isPrime = new boolean[max+1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for(int i = 2; i<=Math.sqrt(max); i++){
            if(!isPrime[i]) continue;
            for(int j = i*i; j<=max; j+=i){
                isPrime[j] = false;
            }
        }
        for(int i:set){
            if(isPrime[i]) answer++;
        }
        return answer;
    }
    static void DFS(String s, String numbers){
        for(int i = 0; i < checked.length; i++){
            if(!checked[i]){
              checked[i] = true;
              int a = Integer.parseInt(s+numbers.charAt(i));
              set.add(a);
              max = Math.max(max,a);
              DFS(s+numbers.charAt(i), numbers);
              checked[i] = false;
            }
        }
    }
}