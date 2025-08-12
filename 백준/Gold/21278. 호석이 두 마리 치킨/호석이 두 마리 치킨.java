import java.io.*;
import java.util.*;
public class Main
{
    static final int INF = 100000;
    public static void main(String[] args) throws Exception
    {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] dist = new int[N+1][N+1];
        for(int i = 1; i <= N; i++){
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        while(M-->0){
            int A = sc.nextInt();
            int B = sc.nextInt();
            dist[A][B] = 1;
            dist[B][A] = 1;
        }
        for(int k = 1; k <= N; k++){
            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= N; j++){
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        int minDist = INF;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[0]-b[0]);
        for(int i = 1; i < N; i++){
            for(int j = i+1; j<=N; j++){
                int totalDist = 0;
                for(int k = 1; k<=N; k++){
                    if(k != i && k != j){
                        totalDist += Math.min(dist[k][i],dist[k][j]) * 2;
                    }
                }
                if(totalDist < minDist){
                    minDist = totalDist;
                    pq.clear();
                    pq.add(new int[]{i, j});
                } else if(totalDist == minDist){
                    pq.add(new int[]{i, j});
                }
            }
        }
        PriorityQueue<int[]> pq2 = new PriorityQueue<>((a,b)->a[1]-b[1]);
        for(int[] arr: pq){
            if(arr[0] == pq.peek()[0]){
                pq2.add(arr);
            }
        }
        int[] ans = pq2.poll();
        System.out.println(ans[0] + " " + ans[1] + " " + minDist);
//        bw.flush();
//        bw.close();
//        br.close();
    }
}