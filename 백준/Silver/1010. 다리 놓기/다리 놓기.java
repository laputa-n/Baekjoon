import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for(int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            bw.write(String.valueOf(comb(b, a)) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static long comb(int n, int r){
        r = Math.min(r, n - r);
        long result = 1;
        for(int i = 1; i <= r; i++){
            result *= (n - i + 1);
            result /= i;
        }
        return result;
    }
}
