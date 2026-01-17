import java.io.*;
import java.util.*;

public class Main {
    /*
    N 세로선 개수
    H 가로선 개수
    M 원래 선 개수
     */
    static int N,M,H;
    static boolean[][] w; //가로선 유무 w[1][2] = true -> 세로로 2번째에 3,4번 잇는 가로선 존재
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        w = new boolean[H][N-1];
        String[] input;
        while(M-->0){
            input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]) - 1;
            int b = Integer.parseInt(input[1]) - 1;
            w[a][b] = true;
        }
        for(int i = 0; i<4; i++){
            if(DFS(0,0,i)){
                bw.write(String.valueOf(i));
                bw.flush();
                return;
            }
        }
        bw.write("-1");
        bw.flush();
        bw.close();
        br.close();
    }
    static boolean DFS(int start, int draw, int limit){
        if(draw == limit){
            for(int i  = 0; i<N; i++){
                if(go(i) != i)
                    return false;
            }
            return true;
        }
        for(int i = start; i<H*(N-1); i++){
            int row = i/(N-1);
            int col = i%(N-1);

            if(w[row][col]) continue;
            boolean isExist = false;
            if (col > 0 && w[row][col-1]) isExist = true;
            if (col < N-2 && w[row][col+1]) isExist = true;
            if(isExist) continue;

            w[row][col] = true;
            if(DFS(i+1,draw+1,limit)) return true;
            w[row][col] = false;
        }
        return false;
    }
    static int go(int start){
        int curH = 0;
        int curW = start;
        while(curH<H){
            if(curW == 0){
                if(w[curH][curW]){
                    curW++;
                }
            } else if(curW == N-1){
                if(w[curH][N-2]){
                    curW--;
                }
            } else {
                if(w[curH][curW]){
                    curW++;
                } else if(w[curH][curW-1]){
                    curW--;
                }
            }
            curH++;
        }
        return curW;
    }
}
