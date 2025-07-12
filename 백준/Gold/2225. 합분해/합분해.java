import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N+1];
        Arrays.fill(arr,1);
        for(int i = 1; i<K; i++){
            for(int j = 1; j<=N; j++){
                arr[j] = (arr[j] + arr[j-1]) % 1000000000;
            }
        }
        bw.write(String.valueOf(arr[N]));
        bw.flush();
        bw.close();
        br.close();
    }
}



