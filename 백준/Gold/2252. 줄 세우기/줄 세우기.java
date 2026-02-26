import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<Integer>[] graph = new List[N+1];
        for(int i = 0; i<=N; i++){
            graph[i] = new ArrayList<>();
        }
        int[] indegree = new int[N+1];
        Arrays.fill(indegree,0);
        while(M-->0){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph[A].add(B);
            indegree[B]++;
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i = 1; i<=N; i++){
            if(indegree[i] == 0){
                q.add(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()){
            int cur = q.poll();
            sb.append(cur).append(" ");
            for(int n:graph[cur]){
                indegree[n]--;
                if(indegree[n] == 0){
                    q.add(n);
                }
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
