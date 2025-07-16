import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[][] matrix = new int[n+1][n+1];
        int[][] arr = new int[n+1][n+1];
        boolean[][] check = new boolean[n+1][n+1];
        StringTokenizer st;
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                arr[i][j] = matrix[i][j];
            }
        }
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    if(j == k || i == j || i == k) continue;
                    if(matrix[i][k] + matrix[k][j] < matrix[i][j]) {
                        bw.write("-1");
                        bw.flush();
                        bw.close();
                        br.close();
                        return;
                    }
                    if(matrix[i][j] == matrix[i][k] + matrix[k][j]) {
                        arr[i][j] = Integer.MAX_VALUE;
                    }
                }
            }
        }
        int sum = 0;
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(i != j && arr[i][j] != Integer.MAX_VALUE && !check[i][j]) {
                    sum += arr[i][j];
                    check[i][j] = check[j][i] = true;
                }
            }
        }
        bw.write(String.valueOf(sum));
        bw.flush();
        bw.close();
        br.close();
    }
}



