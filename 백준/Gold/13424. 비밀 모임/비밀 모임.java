import java.io.*;
import java.util.*;
public class Main
{
    static final int INF = 1000000000;
    public static void main(String args[]) throws Exception
    {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while(T-- > 0){
            int N = sc.nextInt();
            int M = sc.nextInt();
            int[][] dist = new int[N+1][N+1];
            for(int i = 1; i <= N; i++){
                Arrays.fill(dist[i], INF);
                dist[i][i] = 0;
            }
            while(M-->0){
                int a = sc.nextInt();
                int b = sc.nextInt();
                int c = sc.nextInt();
                dist[a][b] = c;
                dist[b][a] = c;
            }
            for(int k = 1; k <= N; k++){
                for(int i = 1; i <= N; i++){
                    for(int j = 1; j <= N; j++){
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
            int K  =sc.nextInt();
            int[] roomNum = new int[K];
            for(int i = 0; i<K; i++){
                roomNum[i] = sc.nextInt();
            }
            int minDist = INF;
            int targetRoomNum = 1;
            for(int i = 1; i<=N; i++){
                int totalDist = 0;
                for(int num:roomNum){
                    totalDist += dist[num][i];
                }
                if(totalDist < minDist){
                    minDist = totalDist;
                    targetRoomNum = i;
                }
            }
            System.out.println(targetRoomNum);
        }
//        bw.flush();
//        bw.close();
//        br.close();
    }
}