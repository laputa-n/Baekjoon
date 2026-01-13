import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        String[] line = br.readLine().split(" ");
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(line[i]);
        Arrays.sort(arr);
        long cnt = 0;
        for(int i = 0; i<N-2; i++){
            int left = i+1;
            int right = N-1;
            while(left < right){
                int sum = arr[i] + arr[left] + arr[right];
                if(sum < 0){
                    left++;
                } else if(sum>0){
                    right--;
                }
                else {
                    if(arr[left] == arr[right]){
                        long m = right - left + 1;
                        cnt += m*(m-1) / 2;
                        break;
                    } else {
                        int lv = arr[left];
                        int rv = arr[right];
                        long lc = 0; long rc = 0;
                        while(left<right && arr[left] == lv){
                            lc++;
                            left++;
                        }
                        while(left<=right && arr[right] == rv){
                            rc++;
                            right--;
                        }
                        cnt += (lc*rc);
                    }
                }
            }
        }
        bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
        br.close();
    }
}
