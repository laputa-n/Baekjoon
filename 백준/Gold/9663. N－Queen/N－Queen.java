import java.io.*;

public class Main{
    static int N;
    static int[] chess;
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        chess = new int[N];
        nQueen(0);
        bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
        br.close();
    }
    static void nQueen(int depth){
        if(depth == N){
            cnt++;
            return;
        }
        for(int i=0; i<N; i++){
            chess[depth] = i;
            if(possible(depth)){
                nQueen(depth+1);
            }
        }
    }
    static boolean possible(int row){
        for(int i=0; i<row; i++){
            if(chess[row] == chess[i]) return false;
            else if(Math.abs(row-i) == Math.abs(chess[row]-chess[i])) return false;
        }
        return true;
    }
}