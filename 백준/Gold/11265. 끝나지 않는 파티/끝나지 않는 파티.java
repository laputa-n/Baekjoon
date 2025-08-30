import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] matrix = new int[N+1][N+1];
        for(int i = 1; i<=N; i++){
            String[] line = br.readLine().split(" ");
            for(int j = 1; j<=N; j++){
                matrix[i][j] = Integer.parseInt(line[j-1]);
            }
        }

        for(int k = 1; k<=N; k++){
            for(int i = 1; i<=N; i++){
                for(int j = 1; j<=N; j++){
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                }
            }
        }

        while(M-->0){
            String[] line = br.readLine().split(" ");
            int A = Integer.parseInt(line[0]);
            int B = Integer.parseInt(line[1]);
            int C = Integer.parseInt(line[2]);
            if(matrix[A][B] <= C){
                bw.write("Enjoy other party\n");
            } else {
                bw.write("Stay here\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}