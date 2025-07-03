import java.io.*;
import java.util.*;

public class Main {
    static int N,M;
    static boolean[][] possible;
    static int max,ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        possible = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String pos = st.nextToken();
            for(int j = 0; j < M; j++) {
                if(pos.charAt(j) == 'Y'){
                    possible[i][j] = true;
                }
            }
        }

        ans = -1;
        max = 0;

        combi();
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
    public static void combi(){
        for(int i = 1; i< (1<<N); i++){
            countSong(i);
        }
    }
    public static void countSong(int g){
        boolean[] temp = new boolean[M];
        for(int i = 0; i<N; i++){
            if((g & (1<<i)) != 0){
                for(int j = 0; j<M; j++){
                    if(possible[i][j]){
                        temp[j] = true;
                    }
                }
            }
        }
        int cnt = 0;
        for(int i = 0; i<M; i++){
            if(temp[i]) cnt++;
        }

        if(cnt == max){
            ans = Math.min(ans, Integer.bitCount(g));
        } else if(cnt > max){
             max = cnt;
             ans = Integer.bitCount(g);
        }
    }
}
