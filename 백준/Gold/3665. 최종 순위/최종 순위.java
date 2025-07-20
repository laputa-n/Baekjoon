    import java.io.*;
    import java.util.*;

    public class Main {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st;
            int T = Integer.parseInt(br.readLine());
            while (T-- > 0) {
                int n = Integer.parseInt(br.readLine());
                int[] seq = new int[n+1];
                st = new StringTokenizer(br.readLine());
                for (int i = 1; i <= n; i++) {
                    seq[i] = Integer.parseInt(st.nextToken());
                }
                int[] edgeCount = new int[n+1];
                ArrayList<Integer>[] adj = new ArrayList[n+1];
                for (int i = 1; i <= n; i++) {
                    adj[i] = new ArrayList<>();
                }
                for (int i = 1; i <= n; i++) {
                    for(int j = i+1; j <= n; j++) {
                        adj[seq[i]].add(seq[j]);
                        edgeCount[seq[j]]++;
                    }
                }
                int m = Integer.parseInt(br.readLine());
                while (m-- > 0) {
                    st = new StringTokenizer(br.readLine());
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());
                    if(adj[b].contains(a)) {
                        adj[a].add(b);
                        edgeCount[a]--;
                        adj[b].remove(Integer.valueOf(a));
                        edgeCount[b]++;
                    } else {
                        adj[b].add(a);
                        edgeCount[b]--;
                        adj[a].remove(Integer.valueOf(b));
                        edgeCount[a]++;
                    }
                }
                Queue<Integer> q = new LinkedList<>();
                for(int i = 1; i <= n; i++) {
                    if(edgeCount[i] == 0) {
                        q.add(i);
                    }
                }
                List<Integer> ans = new ArrayList<>();
                boolean uncertain = false;
                boolean impossible = false;
                for(int i = 0; i<n; i++){
                    if(q.isEmpty()){
                        impossible = true;
                        break;
                    }
                    if(q.size() > 1){
                        uncertain = true;
                    }
                    int cur = q.poll();
                    ans.add(cur);
                    for(int x: adj[cur]){
                        edgeCount[x]--;
                        if(edgeCount[x] == 0) {
                            q.add(x);
                        }
                    }
                }
                if(impossible){
                    bw.write("IMPOSSIBLE\n");
                } else if(uncertain){
                    bw.write("?\n");
                } else {
                    for(int val: ans){
                        bw.write(String.valueOf(val) + " ");
                    }
                    bw.write("\n");
                }
            }
            bw.flush();
            bw.close();
            br.close();
        }
    }
