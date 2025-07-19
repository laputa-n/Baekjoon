import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        int s = 0;
        int e = 1;
        int minDiff = Integer.MAX_VALUE;
        while (e < N) {
            int diff = arr[e] - arr[s];  // arr는 오름차순 정렬되어 있으므로 음수 걱정 없음
            if (diff < M) {
                e++;
            } else {
                minDiff = Math.min(minDiff, diff);
                s++;
                if (s == e) e++;  // 포인터 겹치지 않도록
            }
        }
        bw.write(String.valueOf(minDiff));
        bw.flush();
        bw.close();
        br.close();
    }
}
