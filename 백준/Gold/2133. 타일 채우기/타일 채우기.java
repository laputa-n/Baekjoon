import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        if(N%2 == 1){
            bw.write("0");
        } else {
            int[] dp = new int[31];
            dp[2] = 3;
            for(int i = 4; i<=N; i+=2){
                dp[i] = dp[i-2] * 3;
                for(int j = i-4; j>=2; j-=2){
                    dp[i] += dp[j] * 2;
                }
                dp[i] += 2;
            }
            bw.write(String.valueOf(dp[N]));
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
