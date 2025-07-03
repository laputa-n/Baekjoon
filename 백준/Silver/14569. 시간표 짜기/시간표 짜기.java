import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N  = Integer.parseInt(br.readLine());
        long[] classes = new long[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            for(int j = 0; j < k; j++) {
                int t = Integer.parseInt(st.nextToken());
                classes[i] |= (1L <<(t-1));
            }
        }
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long sTime = 0;
            int cnt = 0;
            int k = Integer.parseInt(st.nextToken());
            for(int j = 0; j < k; j++) {
                int t = Integer.parseInt(st.nextToken());
                sTime |= (1L <<(t-1));
            }
            for(int j = 0; j < N; j++) {
                if(classes[j] == (sTime & classes[j]))
                    cnt++;
            }
            bw.write(String.valueOf(cnt) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

}
