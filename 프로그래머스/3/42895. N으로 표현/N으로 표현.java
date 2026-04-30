import java.util.*;
class Solution {
    public int solution(int N, int number) {
        // + - * / ()
        if(N == number) return 1;
        
        List<Set<Integer>> list = new ArrayList<>();
        for(int i = 0; i<=8; i++){
            list.add(new HashSet<Integer>());
        }
        
        list.get(1).add(N);
        String s = String.valueOf(N);
        for(int i = 2; i<=8; i++){
            s += String.valueOf(N);
            list.get(i).add(Integer.parseInt(s));
            for(int j = 1; j<=i/2; j++){
                Set<Integer> a = list.get(j);
                Set<Integer> b = list.get(i-j);
                for(int x: a){
                    for(int y: b){
                        list.get(i).add(x+y);
                        list.get(i).add(x*y);
                        list.get(i).add(x-y);
                        list.get(i).add(y-x);
                        if(y != 0) list.get(i).add(x/y);
                        if(x != 0) list.get(i).add(y/x);
                    }
                }
            }
            if(list.get(i).contains(number)) return i;
        }
        
        return -1;
    }
}