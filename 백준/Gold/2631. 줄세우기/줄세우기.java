import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        int[] dp = new int[N];
        Arrays.fill(dp, 1);
        int lis = 1;
        for(int i = 1; i < N; i++) {
            for(int j = 0; j < i; j++) {
                if(arr[i]>arr[j]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            lis = Math.max(lis,dp[i]);
        }
        bw.write(String.valueOf(N-lis));
        bw.flush();
        bw.close();
        br.close();
    }
}