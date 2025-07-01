import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] time = new int[N+1];
        int[] totalTime = new int[N+1];
        ArrayList<Integer>[] before = new ArrayList[N+1];
        ArrayList<Integer>[] after = new ArrayList[N+1];
        int[] edgeCount = new int[N+1];
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < N+1; i++) {
            before[i] = new ArrayList<>();
            after[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine()," ");
            time[i] = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            for(int j=0; j<b; j++){
                int w = Integer.parseInt(st.nextToken());
                before[i].add(w);
                after[w].add(i);
            }
            edgeCount[i] += b;
        }
        for(int i = 1; i<=N; i++){
            if(edgeCount[i] == 0)
                q.add(i);
        }
        while(!q.isEmpty()){
            int now = q.poll();
            int m = 0;
            for(int b:before[now]){
                m = Math.max(totalTime[b],m);
            }
            totalTime[now] = m + time[now];
            for(int a:after[now]){
                edgeCount[a]--;
                if(edgeCount[a] == 0)
                    q.add(a);
            }
        }
        int result = 0;
        for(int t: totalTime){
            result = Math.max(result,t);
        }
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }
}
