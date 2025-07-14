import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] house;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        house = new int[N];
        for (int i = 0; i < N; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(house);
        int lo  = 1;
        int hi = house[N-1] - house[0] + 1;
        while(lo < hi){
            int mid = (lo + hi)/2;

            if(caninstall(mid) < C){
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        bw.write(String.valueOf(lo - 1));
        bw.flush();
        bw.close();
        br.close();
    }
    static int caninstall(int d){
        int cnt = 1;
        int recentInstall = house[0];
        for (int i = 1; i < N; i++) {
            int c = house[i];
            if(c - recentInstall >= d){
                recentInstall = c;
                cnt++;
            }
        }
        return cnt;
    }
}



