import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] seq = new int[N];
        int[] LIS = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }
        LIS[0] = seq[0];
        int lengthOfLIS = 1;
        for(int i = 1; i<N; i++){
            int key = seq[i];

            if(LIS[lengthOfLIS-1] < key){
                lengthOfLIS++;
                LIS[lengthOfLIS -1] = key;
            } else {
                int lo = 0;
                int hi = lengthOfLIS;
                while(lo < hi){
                    int mid = (lo + hi)/2;
                    if(LIS[mid] < key){
                        lo = mid + 1;
                    } else {
                        hi = mid;
                    }
                }
                LIS[lo] = key;
            }
        }
        bw.write(String.valueOf(lengthOfLIS));
        bw.flush();
        bw.close();
        br.close();
    }
}



