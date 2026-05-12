class Solution {
    public int solution(int[] money) {
        int size = money.length;
        int[] dpO = new int[size];
        int[] dpX = new int[size];
        
        dpO[0] = dpO[1] = money[0];
        dpX[1] = money[1];
        
        for(int i = 2; i<size; i++){
            dpO[i] = Math.max(money[i] + dpO[i-2] , dpO[i-1]);
            dpX[i] = Math.max(money[i] + dpX[i-2], dpX[i-1]);
        }
        
        return Math.max(dpO[size-2], dpX[size-1]);
    }
}