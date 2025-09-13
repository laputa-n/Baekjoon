import java.io.*;
import java.util.*;
public class Main{
    static int minTime = Integer.MAX_VALUE;
    static int cnt = 0;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        BFS(N,K);
        bw.write(String.valueOf(minTime) + "\n" +  String.valueOf(cnt));
        bw.flush();
        bw.close();
        br.close();
    }
    static void BFS(int start, int end){
        Queue<Integer> q =new ArrayDeque<>();
        q.add(start);
        int[] move = new int[100001];
        while(!q.isEmpty()){
            int now = q.poll();
            if(move[now] > minTime)
                return;
            if(now == end){
                if(move[now] <= minTime){
                    minTime = move[now];
                    cnt++;
                }
            }

            int[] next = {now-1,now+1,now*2};
            for(int n : next){
                if(n < 0 || n > 100000) continue;
                if(move[n] == 0 || move[n] >= move[now]+1){
                    move[n] = move[now]+1;
                    q.add(n);
                }
            }
        }
    }
}