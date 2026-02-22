class Solution {
    static boolean[] visited;
    static int maxVisited = 0;
    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        DFS(k,dungeons,0);
        return maxVisited;
    }
    static void DFS(int tired, int[][] dungeons,int visitCnt){
        if(visited.length == visitCnt || tired == 0)
            return;
        for(int i = 0; i<visited.length; i++){
            if(!visited[i] && tired >= dungeons[i][0]){
                visited[i] = true;
                maxVisited = Math.max(maxVisited, visitCnt+1);
                DFS(tired - dungeons[i][1],dungeons,visitCnt+1);
                visited[i] = false;
            }
        }
    }
}