    import java.io.*;
    import java.util.*;

    public class Main {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            int N = Integer.parseInt(br.readLine());
            int[] edgeCount = new int[N+1];
            int[] time = new int[N+1];
            int[] completeTime = new int[N+1];
            ArrayList<Integer>[] adj = new ArrayList[N+1];
            for (int i = 1; i <= N; i++) {
                adj[i] = new ArrayList<>();
            }
            StringTokenizer st;

            for(int i = 1; i<=N; i++){
                st = new StringTokenizer(br.readLine());
                time[i] = Integer.parseInt(st.nextToken());
                int prev = Integer.parseInt(st.nextToken());
                edgeCount[i] = prev;
                for(int j =0; j<prev; j++){
                    adj[Integer.parseInt(st.nextToken())].add(i);
                }
            }

            Queue<Integer> q = new LinkedList<>();
            for(int i = 1; i<=N; i++){
                if(edgeCount[i] == 0){
                    q.add(i);
                }
            }
            while(!q.isEmpty()){
                int cur = q.poll();
                completeTime[cur] += time[cur];
                for(int x: adj[cur]){
                    completeTime[x] = Math.max(completeTime[x], completeTime[cur]);
                    if(--edgeCount[x] == 0)
                        q.add(x);
                }
            }
            int max = 0;
            for(int i = 1; i<=N; i++){
                max = Math.max(max, completeTime[i]);
            }
            bw.write(String.valueOf(max));
            bw.flush();
            bw.close();
            br.close();
        }
    }
