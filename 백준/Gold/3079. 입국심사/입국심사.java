import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static long[] time;
    static long M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());
        time = new long[N];
        long max = 0;
        for (int i = 0; i < N; i++) {
            time[i] = Long.parseLong(br.readLine());
            max = Math.max(max, time[i]);
        }
        max *= M;
        long ans = lowerbound(1,max);
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
    static long lowerbound(long start, long end){
        while(start < end){
            long mid = (start + end) / 2;
            long res = peopleCount(mid);
            if(res<M) start = mid + 1;
            else end = mid;
        }
        return end;
    }
    static long peopleCount(long t){
        long cnt = 0;
        for(long ti:time){
            cnt += t/ti;
            if(cnt >= M) return cnt;
        }
        return cnt;
    }
}