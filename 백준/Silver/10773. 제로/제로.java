import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int K = Integer.parseInt(br.readLine());
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < K; i++) {
            int x = Integer.parseInt(br.readLine());
            if(x!= 0) dq.offer(x);
            else dq.pollLast();
        }
        int sum = 0;
        for(int val:dq) sum += val;
        bw.write(String.valueOf(sum));
        bw.flush();
        bw.close();
        br.close();

    }
}
