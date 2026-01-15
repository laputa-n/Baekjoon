import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        String[] s = br.readLine().split(" ");
        int[] height = new int[W];
        for (int i = 0; i < W; i++) {
            height[i] = Integer.parseInt(s[i]);
        }
        int amount = 0;
        for(int i = 1; i<W-1; i++){
            int lMax = 0;
            int rMax = 0;
            for(int j = 0; j<i; j++){
                lMax = Math.max(lMax, height[j]);
            }
            for(int j = i+1; j<W; j++){
                rMax = Math.max(rMax, height[j]);
            }
            amount += Math.max(Math.min(lMax,rMax) - height[i],0);
        }
        bw.write(String.valueOf(amount));
        bw.flush();
        bw.close();
        br.close();
    }
}
