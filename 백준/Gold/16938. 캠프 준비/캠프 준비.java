import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int[] level = new int[N];
        String[] temp = br.readLine().split(" ");
        for(int i = 0; i < N; i++){
            level[i] = Integer.parseInt(temp[i]);
        }

        int cnt = 0;
        for(int i = 0; i<1<<N; i++){
            boolean flag = false;
            int sum = 0;
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for(int j = 0; j<N; j++){
                if((i&(1<<j)) != 0){
                    if(sum > R){
                        flag = true;
                        break;
                    }
                    sum += level[j];
                    min = Math.min(min, level[j]);
                    max = Math.max(max, level[j]);
                }
            }
            if(!flag){
                if(sum >= L && sum <= R && max-min >= X)
                    cnt++;
            }
        }
        bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
        br.close();
    }
}