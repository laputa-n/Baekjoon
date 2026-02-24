class Solution {
    public int solution(String name) {
        int len = name.length();
        int cnt = 0;
        for(char c:name.toCharArray()) {
            int diff = c-'A';
            cnt += Math.min(diff,26-diff);
        }

        int min = len-1;
        for(int i = 0; i<len; i++){
            int j = i+1;
            while(j < len && name.charAt(j) == 'A') j++;
            /*
            0 ~ i 노상관
            i+1 ~ j-1 'A'
            j~len-1
            */
            min = Math.min(2*i + len-j, min);
            min = Math.min(2*(len - j) + i, min);
        }

        return cnt + min;
    }
}