import java.io.*;
import java.util.*;

public class Main {
    static String[] data;
    static int N,K,answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int mask = 0;
        mask = mask | 1<<((int)(0));
        mask |= 1<<((int)('c' - 'a'));
        mask |= 1<<((int)('i' - 'a'));
        mask |= 1<<((int)('n' - 'a'));
        mask |= 1<<((int)('t' - 'a'));

        data = new String[N];
        for(int i =0; i<N; i++) {
            String s = br.readLine();
            s = s.replaceAll("[acint]","");
            data[i] = s;
        }
        test(0,5,mask);
        System.out.println(answer);
//        bw.flush();
//        bw.close();
        br.close();
    }
    static void test(int idx, int cnt, int mask) {
        if(cnt == K){
            int canRead = 0;
            for(int i = 0; i<N; i++){
                boolean flag = true;
                for(int j = 0; j<data[i].length(); j++){
                    if((mask & (1<<((int)data[i].charAt(j) - 'a'))) == 0){
                        flag = false;
                        break;
                    }
                }
                if(flag)
                    canRead++;
            }
            answer = Math.max(canRead,answer);
        }
        for(int i = idx; i<26; i++){
            if((mask & (1<<i)) == 0){
                test(i+1, cnt+1, mask | (1<<i));
            }
        }
    }
}
