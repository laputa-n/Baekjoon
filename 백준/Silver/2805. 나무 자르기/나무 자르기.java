import java.io.*;

public class Main {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        arr = new int[N];
        input = br.readLine().split(" ");
        int start = 0;
        int end = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(input[i]);
            end = Math.max(end, arr[i]);
        }
        while(start < end){
            int mid = start + (end - start)/2;
            long get = cut(mid);
            if(get >= M){
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        bw.write(String.valueOf(end-1));
        bw.flush();
        bw.close();
        br.close();
    }
    static long cut(int h){
        long sum = 0;
        for(int t:arr){
            sum += Math.max(t-h,0);
        }
        return sum;
    }
}
