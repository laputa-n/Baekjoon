import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[][] wire = new int[N][2];
        int[] dp = new int[N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            wire[i][0] = Integer.parseInt(st.nextToken());
            wire[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(wire, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[0] - o2[0];
            }
        });

        for(int i = 0; i<N; i++){
            dp[i] = 1;
            for(int j = 0; j<i; j++){
                if(wire[i][1] > wire[j][1]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
        }
        int max = 0;
        for(int val:dp){
            max = Math.max(max,val);
        }
        bw.write(String.valueOf(N-max));
        bw.flush();
        bw.close();
        br.close();
    }
}



