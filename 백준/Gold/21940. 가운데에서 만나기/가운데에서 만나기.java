import java.io.*;
import java.util.*;

public class Main{
    static final int INF = 1000000;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] mat = new int[N+1][N+1];
        for(int i = 0; i<=N; i++){
            Arrays.fill(mat[i], INF);
            mat[i][i] = 0;
        }
        while(M-->0){
            String[] input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            int c = Integer.parseInt(input[2]);
            mat[a][b] = c;
        }
        for(int k = 1; k<=N; k++){
            for(int i = 1; i<=N; i++){
                for(int j = 1; j<=N; j++){
                    mat[i][j] = Math.min(mat[i][j], mat[i][k] + mat[k][j]);
                }
            }
        }
        int K = Integer.parseInt(br.readLine());
        int[] idx = new int[K];
        String[] input = br.readLine().split(" ");
        for(int i = 0; i<K; i++){
            idx[i] = Integer.parseInt(input[i]);
        }

        int min = Integer.MAX_VALUE;
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 1; i<=N; i++){
            int max = Integer.MIN_VALUE;
            boolean flag = false;
            for(int j: idx){
                if(mat[i][j] == INF || mat[j][i] == INF){
                    flag = true;
                    break;
                }
                int x = mat[j][i] + mat[i][j];
                if( x>max){
                    max = x;
                }
            }
            if(flag) continue;
            if(max < min){
                min = max;
                list.clear();
                list.add(i);
            } else if(max == min){
                list.add(i);
            }
        }

        Collections.sort(list);
        for(int i : list){
            bw.write(String.valueOf(i) + " ");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}