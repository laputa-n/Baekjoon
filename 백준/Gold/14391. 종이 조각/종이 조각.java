import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] paper = new int[N][M];
        for(int i = 0; i < N; i++) {
            String s = br.readLine();
            for(int j = 0; j < M; j++) {
                paper[i][j] = s.charAt(j) - '0';
            }
        }
        int ans = 0;
        for(int s = 0; s< 1<<(N*M); s++){
            int sum = 0;

            for(int i = 0; i<N; i++){
                int temp = 0;
                for(int j = 0; j<M; j++){
                    int k = i*M+j;
                    if((s&(1<<k)) == 0){
                        temp *= 10;
                        temp += paper[i][j];
                    } else {
                        sum += temp;
                        temp = 0;
                    }
                }
                sum += temp;
            }

            for(int i = 0; i<M; i++){
                int temp = 0;
                for(int j = 0; j<N; j++){
                    int k = i + j*M;
                    if((s&(1<<k)) != 0){
                        temp *= 10;
                        temp += paper[j][i];
                    }else {
                        sum += temp;
                        temp = 0;
                    }
                }
                sum += temp;
            }

            ans = Math.max(ans,sum);
        }
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}
