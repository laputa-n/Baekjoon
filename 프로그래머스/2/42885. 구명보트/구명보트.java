import java.util.*;
class Solution {
    public int solution(int[] people, int limit){
        int cnt = 0;
        int n = people.length;
        Arrays.sort(people);
        int idx = 0;
        for(int i = n-1; i>=idx; i--){
            if(people[i] + people[idx] <= limit){
                idx++;
            }
            cnt++;
        }
        return cnt;
    }
}