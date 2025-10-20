    import java.io.*;
    import java.util.*;

    public class Main{
        public static void main(String[] args) throws Exception{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            int T = Integer.parseInt(br.readLine());
            while(T-->0){
                int k = Integer.parseInt(br.readLine());
                int n = Integer.parseInt(br.readLine());
                int[][] matrix = new int[k+1][n+1];
                for(int i = 1; i<=n; i++){
                    matrix[0][i] = i;
                }

                for(int i = 1; i<=k; i++){
                    for(int j = 1; j<=n; j++){
                        matrix[i][j] = matrix[i-1][j] + matrix[i][j-1];
                    }
                }

                bw.write(String.valueOf(matrix[k][n]) + "\n");
            }
            bw.flush();
            bw.close();
            br.close();
        }
    }