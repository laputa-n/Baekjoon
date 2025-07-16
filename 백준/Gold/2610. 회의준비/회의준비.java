import java.io.*;
import java.util.*;

public class Main {
    static final int INF = (int) Math.pow(10,8);
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[][] matrix = new int[N+1][N+1];
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                if(i == j) matrix[i][j] = 0;
                else matrix[i][j] = INF;
            }
        }
        StringTokenizer st;
        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            matrix[a][b] = 1;
            matrix[b][a] = 1;
        }

        for(int k = 1; k <= N; k++){
            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= N; j++){
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                }
            }
        }

        List<List<Integer>> groups = getGroups(matrix, N);
        StringBuilder sb = new StringBuilder();
        sb.append(groups.size()).append("\n");
        List<Integer> candidates = new ArrayList<>();
        for (List<Integer> group : groups) {
            int candidate = 0;
            int num = INF;
            for (int j : group) {
                int t = 0;
                for (int k : group) {
                    if (j == k) continue;
                    t = Math.max(t, matrix[j][k]);
                }
                if (t < num) {
                    num = t;
                    candidate = j;
                }
            }
            candidates.add(candidate);
        }
        candidates.sort(Comparator.naturalOrder());
        for(int x: candidates){
            sb.append(x).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    public static List<List<Integer>> getGroups(int[][] matrix, int N){
        boolean[] visited = new boolean[N+1];
        List<List<Integer>> groups = new ArrayList<>();

        for(int i = 1; i<=N; i++){
            if(!visited[i]){
                List<Integer> group = new ArrayList<>();
                Queue<Integer> q = new LinkedList<>();
                q.offer(i);
                visited[i] = true;
                while(!q.isEmpty()){
                    int cur = q.poll();
                    group.add(cur);
                    for(int j = 1; j<=N; j++){
                        if(matrix[cur][j] != INF && !visited[j]){
                            visited[j] = true;
                            q.offer(j);
                        }
                    }
                }
                groups.add(group);
            }
        }
        return groups;
    }
}



