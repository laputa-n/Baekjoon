import java.io.*;
import java.util.*;

public class Main {
    static int N,S,M;
    static int[] volume;
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        S = Integer.parseInt(input[1]);
        M = Integer.parseInt(input[2]);
        volume= new int[N];
        input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            volume[i] = Integer.parseInt(input[i]);
        }
        dp = new int[N][M+1];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }
        bw.write(String.valueOf(search(0,S)));
        bw.flush();
        bw.close();
        br.close();
    }
    static int search(int index, int value){
        if(value > M || value < 0)
            return -1;

        if(index == N)
            return value;

        if(dp[index][value] != Integer.MIN_VALUE)
            return dp[index][value];

        return dp[index][value] = Math.max(dp[index][value],
                Math.max(search(index+1, value + volume[index]), search(index+1, value - volume[index])));
    }
}