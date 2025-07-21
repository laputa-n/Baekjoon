    import java.io.*;
    import java.util.*;

    public class Main {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer  st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[] edgeCount = new int[N+1];
            ArrayList<Integer> [] adj =  new ArrayList[N+1];
            for(int i = 1; i <= N; i++){
                adj[i] = new ArrayList<>();
            }
            Queue<Integer> q = new LinkedList<>();
            int[] time = new int[N+1];
            for(int i = 0; i<M; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                edgeCount[b]++;
                adj[a].add(b);
            }

            for(int i =1; i<=N; i++){
                if(edgeCount[i] == 0){
                    q.add(i);
                    time[i] = 1;
                }
            }
            while(!q.isEmpty()){
                int u = q.poll();
                for(int v: adj[u]){
                    if(--edgeCount[v] == 0){
                        q.add(v);
                        time[v] = time[u] + 1;
                    }
                }
            }
            for(int i = 1; i<=N; i++){
                bw.write(String.valueOf(time[i]) + " ");
            }
            bw.flush();
            bw.close();
            br.close();
        }
    }
