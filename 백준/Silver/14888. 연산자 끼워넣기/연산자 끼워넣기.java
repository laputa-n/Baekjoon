import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] nums;
    static int[] op;
    static int MAX;
    static int MIN;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        MAX = -1000000000;
        MIN = 1000000000;
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        op = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            op[i] = Integer.parseInt(st.nextToken());
        }
        bfs(nums[0],1);
        bw.write(String.valueOf(MAX) + "\n" + String.valueOf(MIN));
        bw.flush();
        bw.close();
        br.close();
    }
    static void bfs(int num, int idx){
        if(idx == N){
            MAX = Math.max(MAX,num);
            MIN = Math.min(MIN,num);
            return;
        }

        for(int i = 0; i<4; i++){
            if(op[i] != 0){
                op[i]--;
                switch(i){
                    case 0: bfs(num + nums[idx], idx+1); break;
                    case 1: bfs(num - nums[idx], idx+1); break;
                    case 2: bfs(num * nums[idx], idx+1); break;
                    case 3: bfs(num / nums[idx], idx+1); break;
                }
                op[i]++;
            }

        }
    }
}
