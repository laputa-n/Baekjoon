import java.io.*;
import java.util.*;
import java.util.List;

public class Main {
    static long[] DP = new long[55];
    static long calOne(long x){
        if(x==0) return 0;
        long cnt = 1&x;
        int size = (int) (Math.log(x) / Math.log(2));
        for(int i = size; i>0; i--){
            if((x & (1L<<i)) != 0){
                cnt += DP[i-1] + (x- (1L<<i)) + 1;
                x -= (1L<<i);
            }
        }
        return cnt;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        setDP();
        long result = calOne(B) - calOne(A-1);
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }
    static void setDP(){
        DP[0] = 1;
        for(int i = 1; i<55; i++){
            DP[i] = (DP[i-1]<<1) + (1L<<i);
        }
    }
}
