import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] indegrees = new int[N+1];
        List<Integer>[] next = new ArrayList[N+1];
        for(int i = 0; i<=N; i++){
            next[i] = new ArrayList<Integer>();
        }
        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            next[A].add(B);
            indegrees[B]++;
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i = 1; i<=N; i++){
            if(indegrees[i] == 0){
                q.add(i);
            }
        }
        ArrayList<Integer> answer = new ArrayList<Integer>();
        while(!q.isEmpty()){
            int cur = q.poll();
            answer.add(cur);
            for(int adj:next[cur]){
                indegrees[adj]--;
                if(indegrees[adj] == 0){
                    q.add(adj);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int a:answer){
            sb.append(a).append(" ");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}