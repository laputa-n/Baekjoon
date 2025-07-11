import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N  = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] LIS = new int[N];
        int[] LDS = new int[N];
        for (int i = 0; i < N; i++) {
            LIS[i] = 1;
            for(int j = 0; j<i; j++){
                if(arr[i] > arr[j] && LIS[j] + 1 > LIS[i]){
                    LIS[i] = LIS[j] + 1;
                }
            }
        }
        for(int i = N-1; i>=0; i--){
            LDS[i] = 1;
            for(int j = N-1; j>i; j--){
                if(arr[i]>arr[j] && LDS[j] + 1 > LDS[i])
                    LDS[i] = LDS[j] + 1;
            }
        }
        int max = 0;
        for(int i = 0; i < N; i++){
            max = Math.max(LIS[i] + LDS[i] -1,max);
        }
        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
        br.close();
    }
}



