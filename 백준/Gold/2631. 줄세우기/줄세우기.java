import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] dp = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        int last = 0;
        dp[0] = arr[0];
        for(int i = 1; i<N; i++){
            if(arr[i] > dp[last]){
                dp[++last] = arr[i];
            } else {
                int idx = lowerBound(0,last,arr[i],dp);
                dp[idx] = arr[i];
            }
        }
        bw.write(String.valueOf(N-last-1));
        bw.flush();
        bw.close();
        br.close();
    }
    static int lowerBound(int start, int end, int key, int[] ar){
        while(start<end){
            int mid = (start+end)/2;
            if(ar[mid]<key){
                start = mid+1;
            } else {
                end = mid;
            }
        }
        return end;
    }
}