import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }
        int[] lis = new int[n];
        lis[0] = arr[0];
        int idx = 1;
        int tmp = 0;
        for (int i = 1; i < n; i++) {
            if(lis[0] > arr[i]){
                lis[0] = arr[i];
            } else if(lis[idx - 1] < arr[i]){
                lis[idx++] = arr[i];
            } else {
                tmp = Arrays.binarySearch(lis,0,idx,arr[i]);
                lis[tmp < 0 ? (-tmp -1) : tmp] = arr[i];
            }
        }
        bw.write(String.valueOf(idx));
        bw.flush();
        bw.close();
        br.close();
    }
}