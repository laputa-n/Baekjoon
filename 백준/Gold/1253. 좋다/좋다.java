import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        String[] input = br.readLine().split(" ");
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(input[i]);
        }
        Arrays.sort(arr);
        int cnt = 0;
        for(int i = 0; i<N; i++){
            int tar = arr[i];
            int left = 0;
            int right = N-1;
            while(left<right){
                if(left == i){
                    left++;
                    continue;
                }
                if(right == i){
                    right--;
                    continue;
                }
                int sum = arr[left] + arr[right];
                if(sum==tar){
                    cnt++;
                    break;
                } else if(sum<tar){
                    left++;
                } else {
                    right--;
                }
            }
        }
        bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
        br.close();
    }
}
