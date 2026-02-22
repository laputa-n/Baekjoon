class Solution {
    public int[] solution(int brown, int yellow) {
        int[] ans = {0,0};
        for(int h = 1; h <= Math.sqrt(yellow); h++){
        //i는 노란 부분의 높이
            if(yellow%h != 0) continue;
            int w = yellow/h;
            if(brown == 2*(w+2+h))
                ans =  new int[]{w+2,h+2};
        }
        return ans;
    }
}