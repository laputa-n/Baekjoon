import java.io.*;
import java.util.*;

public class Main {
    static int N,H;
    static int[] root,drop;
    static int binarySearch(int left, int right, int key, int[] arr){
        while(left<right){
            int mid = (left+right)/2;
            if(arr[mid] < key){
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return arr.length - right;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        root = new int[N/2];
        drop  = new int[N/2];
        for(int i = 0; i < N/2; i++){
            root[i] = Integer.parseInt(br.readLine());
            drop[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(root);
        Arrays.sort(drop);
        int min = N;
        int totalCnt = 0;
        for(int i = 1; i<H+1; i++){
            int cnt = binarySearch(0,N/2,i,root) + binarySearch(0,N/2,H-i+1,drop);
            if(cnt == min){
                totalCnt++;
                continue;
            }
            if(cnt < min){
                min = cnt;
                totalCnt = 1;
            }
        }
        bw.write(String.valueOf(min) + " " + String.valueOf(totalCnt));
        bw.flush();
        bw.close();
        br.close();
    }

}
