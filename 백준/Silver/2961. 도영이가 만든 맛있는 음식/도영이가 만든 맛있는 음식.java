import java.io.*;
import java.util.*;

public class Main {
    static class ing {
        long sour;
        long bitter;
        ing(long sour, long bitter) {
            this.sour = sour;
            this.bitter = bitter;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        ing[] ings = new ing[N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine()," ");
            ings[i] = new ing(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
        }
        int d = 1<<N;
        long min = Long.MAX_VALUE;
        for(int i = 1; i<d; i++){
            long s = 1;
            long b = 0;
            for(int j = 0; j<N; j++){
                if((i & (1<<j)) != 0){
                    s *= ings[j].sour;
                    b += ings[j].bitter;
                }
            }
            long val = Math.abs(s-b);
            min = Math.min(min, val);
        }
        bw.write(String.valueOf(min));
        bw.flush();
        bw.close();
        br.close();
    }
}
