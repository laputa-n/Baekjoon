import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int K = Integer.parseInt(br.readLine());
        int s = 1;
        while(s < K){
            s *= 2;
        }
        int result1 = s;
        int cnt = 0;
        while(K>0){
            if(K<s){
                s /= 2;
                cnt++;
            } else {
                K -= s;
            }
        }
        bw.write(String.valueOf(result1) + " " + String.valueOf(cnt));
        bw.flush();
        bw.close();
        br.close();
    }
}
