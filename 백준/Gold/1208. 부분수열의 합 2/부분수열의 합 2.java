import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int size = N/2;
        int[] a = new int[1<<(N-size)];
        int[] b= new int[1<<(size)];
        for(int i = 0; i<(1<<(N-size)); i++){
            for(int j = 0; j<(N-size); j++){
                if(((1<<j) & i) != 0){
                    a[i] += arr[j];
                }
            }
        }
        for(int i = 0; i<(1<<size); i++){
            for(int j = 0; j<size; j++){
                if(((1<<j) & i) != 0){
                    b[i] += arr[j + (N-size)];
                }
            }
        }
        Arrays.sort(a);
        Arrays.sort(b);
        long cnt = 0;
        for(int i =0; i<a.length; i++){
            int av = a[i];
            int aTerm = upperBound(a,av) - lowerBound(a,av);
            int bTerm = upperBound(b,S-av) - lowerBound(b,S-av);
            cnt += (long) aTerm *bTerm;
            i += (aTerm-1);
        }
        if(S == 0)
            cnt--;
        bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
        br.close();
    }
    static int upperBound(int[] arr, int t){
        int left = 0; int right = arr.length;
        while(left < right){
            int mid = (left + right)/2;
            if(arr[mid] <= t){
                left = mid + 1;
            } else
                right = mid;
        }
        return right;
    }
    static int lowerBound(int[] arr,int t){
        int left = 0; int right = arr.length;
        while(left < right){
            int mid = (left + right)/2;
            if(arr[mid] < t){
                left = mid + 1;
            } else
                right = mid;
        }
        return right;
    }

}
