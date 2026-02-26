import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] loc;
    static int N,C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        loc = new int[N];
        for(int i = 0; i < N; i++) {
            loc[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(loc);
        int start = 1; int end = loc[N-1] - loc[0] + 1;
        while(start<end){
            int mid = (start+end)/2;
            int cnt = APCount(mid);
            if(cnt>=C){
                start = mid+1;
            } else {
                end = mid;
            }
        }
        bw.write(String.valueOf(end-1));

        bw.flush();
        bw.close();
        br.close();
    }
    static int APCount(int d){
        int cnt = 1;
        int lastPoint = loc[0];
        for(int i = 1; i<N; i++){
            if(loc[i] - lastPoint>=d){
                cnt++;
                lastPoint = loc[i];
            }
        }
        return cnt;
    }

}
