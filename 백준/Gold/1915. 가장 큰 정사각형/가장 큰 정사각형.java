import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 1; j <= m; j++) {
                map[i][j] = chars[j-1] - '0';
            }
        }
        int[][] dp = new int[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1] + map[i][j] - dp[i-1][j-1];
            }
        }
        int ans = 0;
        for(int i =1; i<=n; i++){
            for(int j = 1; j<=m; j++){
                if(map[i][j] == 1){
                    int res = 1;
                    int idx = 1;
                    while(i+idx <= n && j+idx <= m){
                        int space = dp[i+idx][j+idx] - dp[i-1][j+idx] - dp[i+idx][j-1] + dp[i-1][j-1];
                        idx++;
                        if(space != idx*idx)
                            break;
                        res = idx * idx;
                    }
                    ans = Math.max(ans, res);
                }
            }
        }
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }

}
