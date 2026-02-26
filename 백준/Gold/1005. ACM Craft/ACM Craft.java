import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            List<Integer>[] nextList = new List[N+1];
            for(int i = 0; i <= N; i++){
                nextList[i] = new ArrayList<>();
            }
            int[] dp = new int[N+1];
            dp[0] = 0;
            int[] indegree = new int[N+1];
            Arrays.fill(indegree, 0);
            int[] times = new int[N+1];
            st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= N; i++) {
                times[i] = Integer.parseInt(st.nextToken());
            }
            while(K-- > 0) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                nextList[X].add(Y);
                indegree[Y]++;
            }
            int tar = Integer.parseInt(br.readLine());
            Queue<Integer> q = new LinkedList<>();
            for(int i = 1; i <= N; i++) {
                if(indegree[i] == 0) {
                    q.add(i);
                    dp[i] = times[i];
                }
            }
            while(!q.isEmpty()) {
                int cur = q.poll();
                if(cur == tar) break;
                for(int n: nextList[cur]) {
                    dp[n] = Math.max(dp[n], dp[cur] + times[n]);
                    indegree[n]--;
                    if(indegree[n] == 0) {
                        q.add(n);
                    }
                }
            }
            bw.write(String.valueOf(dp[tar]) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
