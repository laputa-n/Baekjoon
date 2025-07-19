import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //접시 수
        int d = Integer.parseInt(st.nextToken()); //초밥의 가짓수
        int k = Integer.parseInt(st.nextToken()); //연속 접시 수
        int c = Integer.parseInt(st.nextToken()); //쿠폰 번호
        int[] dish = new int[N];
        int[] check = new int[d+1];
        for(int i = 0; i < N; i++) {
            dish[i] = Integer.parseInt(br.readLine());
        }
        int res = 1;
        check[c]++;
        for(int i = 0; i<k; i++){
            if(check[dish[i]] == 0) res++;
            check[dish[i]]++;
        }
        int cnt = res;
        for(int i = 1; i<N; i++){
            int pop = dish[i-1];
            check[pop]--;
            if(check[pop] == 0) cnt--;

            int add = dish[(i+k-1)%N];
            if(check[add] == 0) cnt++;
            check[add]++;

            res = Math.max(res,cnt);
        }
        bw.write(String.valueOf(res));
        bw.flush();
        bw.close();
        br.close();
    }
}
