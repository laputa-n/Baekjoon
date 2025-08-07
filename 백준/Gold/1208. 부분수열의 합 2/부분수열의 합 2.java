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
        for(int i = 0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int size = N/2;
        int[] a = new int[(1<<size)];
        int[] b = new int[(1<<(N-size))];
        for(int i = 0; i<(1<<size); i++){
            for(int j = 0; j<size; j++){
                if(((1<<j) & i) != 0){
                    a[i] += arr[j];
                }
            }
        }
        for(int i = 0; i<(1<<(N-size)); i++){
            for(int j =0; j<(N-size); j++){
                if(((1<<j) & i) != 0){
                    b[i] += arr[size + j];
                }
            }
        }
        Arrays.sort(a);
        Arrays.sort(b);

        long cnt = 0;
        int ap = 0;
        int bp = b.length-1;
        while(ap < a.length && bp>= 0){
            int av = a[ap];
            int bv = b[bp];

            if(av+bv == S){
                long ac = 0;
                long bc = 0;
                while(ap < a.length && av == a[ap]){
                    ac++;
                    ap++;
                }
                while(bp >= 0 && bv == b[bp]){
                    bc++;
                    bp--;
                }
                cnt += ((long) ac * bc);
            } else if (av+bv < S){
                ap++;
            } else if (av+bv>S){
                bp--;
            }
        }
        if(S == 0) cnt--;
        bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
        br.close();
    }
}
