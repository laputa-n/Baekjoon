import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] train = new int[N+1];
        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine()," ");
            int cmd = Integer.parseInt(st.nextToken());
            if(cmd == 3){
                int target = Integer.parseInt(st.nextToken());
                train[target] <<= 1;
                train[target] &= ~(1<<20);
            } else if(cmd == 4){
                int target = Integer.parseInt(st.nextToken());
                train[target] = (train[target] >> 1);
            } else if(cmd == 1){
                int target = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());
                train[target] |= (1<<(k-1));
            } else if(cmd == 2){
                int target = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());
                train[target] &=  ~(1<<(k-1));
            }
        }
        Set<Integer> set = new HashSet<>();
        for(int i = 1; i<=N; i++){
            set.add(train[i]);
        }
        bw.write(String.valueOf(set.size()));
        bw.flush();
        bw.close();
        br.close();
    }
}
