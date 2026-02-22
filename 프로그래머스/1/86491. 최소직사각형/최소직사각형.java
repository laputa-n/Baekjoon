class Solution {
    public int solution(int[][] sizes) {
        int w = sizes[0][0];
        int h = sizes[0][1];
        for(int i = 1; i < sizes.length; i++) {
            if(w >= sizes[i][0] && h >= sizes[i][1]) continue;
            if(w >= sizes[i][1] && h >= sizes[i][0]) continue;
            int rightW = Math.max(w,sizes[i][0]);
            int rightH = Math.max(h,sizes[i][1]);
            int rollW = Math.max(w,sizes[i][1]);
            int rollH = Math.max(h,sizes[i][0]);
            int right = rightW * rightH;
            int roll = rollW * rollH;
            if(right > roll){
                w = rollW;
                h = rollH;
            } else {
                w = rightW;
                h = rightH;
            }
        }
        return w*h;
    }
}