import java.io.*;
import java.util.*;

public class Main {
    static int N,M;
    static int[] arr,ans;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        ans = new int[M];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        back(0,0);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    static void back(int idx, int depth){
        if(depth == M){
            for(int val: ans){
                sb.append(val).append(" ");
            }
            sb.append("\n");
            return;
        }
        for(int i = idx; i<N; i++){
            ans[depth] = arr[i];
            back(i, depth+1);
        }
    }
}
