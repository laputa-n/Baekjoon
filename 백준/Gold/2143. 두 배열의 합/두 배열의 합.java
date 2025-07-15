import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        long T = Long.parseLong(br.readLine());

        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        int[] B = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i<n; i++){
            A[i] += A[i-1];
        }
        for(int i = 1; i<m; i++){
            B[i] += B[i-1];
        }

        int aSize = n*(n+1) / 2;
        long[] aSum = new long[aSize];
        int idx = 0;
        for(int i = 0; i<n; i++){
            for(int j = i; j<n; j++){
                int av = A[j];
                if(i>0) av -= A[i-1];
                aSum[idx++] = av;
            }
        }

        int bSize = m*(m+1) / 2;
        long[] bSum = new long[bSize];
        idx = 0;
        for(int i =0; i<m; i++){
            for(int j = i; j<m; j++){
                int bv = B[j];
                if(i>0) bv -= B[i-1];
                bSum[idx++] = bv;
            }
        }
        Arrays.sort(aSum);
        Arrays.sort(bSum);

        int left  = 0;
        int right = bSum.length - 1;
        long cnt = 0;

        while(left < aSize && right >= 0){
            long asv = aSum[left];
            long bsv = bSum[right];
            long sum = asv + bsv;
            if(sum == T){
                long ac = 0; long bc = 0;
                while(left < aSize && asv == aSum[left]){
                    ac++;
                    left++;
                }
                while(right >= 0 && bsv == bSum[right]){
                    right--;
                    bc++;
                }
                cnt += ac*bc;
            } else if(sum>T){
                right--;
            } else {
                left++;
            }
        }
        bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
        br.close();
    }
}



