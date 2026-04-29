
class Solution {
    public int solution(String name) {
        int length = name.length();
        int min = length - 1;
        int cnt = 0;
        for(int i = 0; i<length; i++){
            
            //위,아래
            cnt += Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i)+1);
            
            int idx = i+1;
            while(idx<length && name.charAt(idx) == 'A') idx ++;
            
            min = Math.min(2*i + (length - idx), min);
            min = Math.min((length - idx) * 2 + i , min);
        }
        
        return min + cnt;
        
    }
}