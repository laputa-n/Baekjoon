import java.util.*;
import java.io.*;

import static java.lang.Math.min;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[][] matrix = new int[n][3];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            matrix[i][0] = Integer.parseInt(st.nextToken());
            matrix[i][1] = Integer.parseInt(st.nextToken());
            matrix[i][2] = Integer.parseInt(st.nextToken());
        }
        for(int i = 1; i<n; i++){
            matrix[i][0] += Math.min(matrix[i-1][1], matrix[i-1][2]);
            matrix[i][1] += Math.min(matrix[i-1][0], matrix[i-1][2]);
            matrix[i][2] += Math.min(matrix[i-1][0], matrix[i-1][1]);
        }
        bw.write(String.valueOf(Math.min(Math.min(matrix[n-1][0],matrix[n-1][1]),matrix[n-1][2])));
        
        bw.flush();
        bw.close();
        br.close();
    }
}
