import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        town[] towns = new town[N+1];
        int[][] dist = new int[N+1][N+1];
        for(int i = 1; i <= N; i++){
            String[] line = br.readLine().split(" ");
            towns[i] = new town(
                    line[0].equals("1"),
                    Integer.parseInt(line[1]),
                    Integer.parseInt(line[2])
            );
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i] = 0;
        }
        for(int i = 1; i<=N; i++){
            for(int j = i+1; j<=N; j++){
                boolean isBothSpecial = towns[i].special && towns[j].special;
                if(isBothSpecial){
                    dist[i][j] = Math.min(T,Math.abs(towns[i].row - towns[j].row) + Math.abs(towns[i].col - towns[j].col));
                } else {
                    dist[i][j] = Math.abs(towns[i].row - towns[j].row) + Math.abs(towns[i].col - towns[j].col);
                }
                dist[j][i] = dist[i][j];
            }
        }
        for(int k = 1; k<=N; k++){
            for(int i = 1; i<=N; i++){
                for(int j = 1; j<=N; j++){
                    if(i==k || k == j) continue;
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        int M = Integer.parseInt(br.readLine());
        while(M-->0){
            String[] line = br.readLine().split(" ");
            bw.write(String.valueOf(dist[Integer.parseInt(line[0])][Integer.parseInt(line[1])]) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static class town {
        boolean special;
        int row,col;
        public town(boolean special, int row, int col) {
            this.special = special;
            this.row = row;
            this.col = col;
        }
    }


}