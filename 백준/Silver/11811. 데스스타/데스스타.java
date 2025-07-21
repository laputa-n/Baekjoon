import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[][] matrix = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            for(int j = i+1; j < N; j++) {
                arr[i] = arr[i] | matrix[i][j];
                arr[j] = arr[j] | matrix[i][j];
            }
        }
        for(int val:arr){
            bw.write(String.valueOf(val)+" ");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
