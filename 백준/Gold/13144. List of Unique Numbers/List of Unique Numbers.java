import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;
    static int[] cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        cnt = new int[100001];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        long ans = 0;
        int l = 1;
        int r = 0;
        while(l <= N){
            while(r+1 <= N && cnt[arr[r+1]] == 0){
                r++;
                cnt[arr[r]]++;
            }
            ans += (r-l+1);
            cnt[arr[l++]]--;
        }
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}
