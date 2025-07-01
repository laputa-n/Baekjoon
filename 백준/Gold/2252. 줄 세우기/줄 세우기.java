import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<Integer> [] graph = new ArrayList[N+1];
        for (int i = 0; i < N+1; i++) {
            graph[i] = new ArrayList<>();
        }
        int[] edgeCount = new int[N+1];
        Arrays.fill(edgeCount, 0);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            edgeCount[v]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 1; i<N+1; i++) {
            if(edgeCount[i] == 0)
                queue.add(i);
        }
        while(!queue.isEmpty()) {
            int u = queue.poll();
            bw.write(String.valueOf(u) + " ");
            List<Integer> list = graph[u];
            for(int v : list) {
                edgeCount[v]--;
                if(edgeCount[v] == 0){
                   queue.add(v);
                }
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
