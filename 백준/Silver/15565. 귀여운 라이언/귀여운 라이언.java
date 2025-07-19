import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int minLen = Integer.MAX_VALUE;
        int s = 0, e = 0, lioncnt = 0;
        while(e<N){
            if(arr[e] == 1) lioncnt++;

            while(lioncnt >= K){
                minLen = Math.min(minLen, e-s+1);
                if(arr[s] == 1) lioncnt--;
                s++;
            }
            e++;
        }
        if(minLen == Integer.MAX_VALUE){
            bw.write("-1");
        } else {
            bw.write(String.valueOf(minLen));
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static int lionCount(int[] arr, int s, int e){
        int cnt = 0;
        for(int i = s; i < e; i++){
            if(arr[i] == 1)
                cnt++;
        }
        return cnt;
    }
}
