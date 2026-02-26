import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] ice = new int[1000001];
        Arrays.fill(ice,0);
        int xMax = 0;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int g = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            ice[x] += g;
            xMax = Math.max(x, xMax);
        }
        int window = 2*K;
        long slidingSum = 0;
        for(int i = 0; i<=window && i<=1000000; i++){
            slidingSum += ice[i];
        }
        long ans = 0;
        ans = Math.max(ans,slidingSum);

        for(int i = 1; i+window <=1000000; i++){
            slidingSum -= ice[i-1];
            slidingSum += ice[i+window];
            ans = Math.max(ans,slidingSum);
            if(i+window==xMax) break;
        }
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}
