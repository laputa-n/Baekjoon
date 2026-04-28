class Solution {
    static boolean[] visited;
    static int max = 0;
    static boolean flag = false;
    public int solution(int k, int[][] dungeons) {
        //k: 현재 피로도
        //dungeons[i][0]: i던전 최소 필요 피로도
        //dungeons[i][1]: i던전 소모 피로도
        visited = new boolean[dungeons.length];
        
        //현재 피로도, 개수, 던전 정보
        DFS(k,0, dungeons);
        
        return max;
    }
    static void DFS(int tired, int depth, int[][] d){
        if(flag) return;
        for(int i = 0; i<d.length; i++){
            if(!visited[i] && tired >= d[i][0]){
                visited[i] = true;
                max = Math.max(max,depth+1);
                if(depth+1 == d.length){
                    flag = true;
                    return;
                }
                DFS(tired - d[i][1], depth+1, d);
                visited[i] = false;
            }
        }
        
    }
    
}