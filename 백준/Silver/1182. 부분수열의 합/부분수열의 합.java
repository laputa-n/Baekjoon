import java.io.*;
import java.util.*;

public class Main {
    static int N,S,totalCnt;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine()," ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        totalCnt = 0;
        ArrayList<Integer> sep = new ArrayList<>();
        bfs(0,0, 0);
        bw.write(String.valueOf(totalCnt));
        bw.flush();
        bw.close();
        br.close();
    }
    static void bfs(int cnt, int idx, int sum){
        if(sum == S && cnt > 0){
            totalCnt++;
        }
        if(cnt == N){
            return;
        }
        for(int i = idx; i<N; i++){
            bfs(cnt+1, i+1, sum+arr[i]);
        }
    }
}
