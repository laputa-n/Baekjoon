import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        boolean[][] mat = new boolean[100][100];
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        while(N-->0){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            for(int i = 99-B; i>89-B; i--){
               for(int j = A; j<A+10; j++){
                   mat[i][j] = true;
               }
            }
        }
        int cnt = 0;
        for(int i = 0; i<100; i++){
            for(int j = 0; j<100; j++){
                if(mat[i][j]){
                    cnt++;
                }
            }
        }
        bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
        br.close();
    }
}