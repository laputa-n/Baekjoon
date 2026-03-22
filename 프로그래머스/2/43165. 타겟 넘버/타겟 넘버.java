class Solution {
    static int cnt = 0;
    public int solution(int[] numbers, int target) {
        DFS(numbers, target,0,0);
        return cnt;
    }
    static void DFS(int[] numbers, int target, int depth, int sum){
        if(depth == numbers.length){
            if(sum == target) cnt++;
            return;
        }
        DFS(numbers, target, depth+1, sum+numbers[depth]);
        DFS(numbers, target, depth+1, sum-numbers[depth]);
    }
}