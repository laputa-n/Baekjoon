import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        boolean[] visited = new boolean[n];
        String[] input = br.readLine().split(" ");
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(input[i]);
        }
        int s = Integer.parseInt(br.readLine())-1;
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        visited[s] = true;
        while(!q.isEmpty()){
            int cur = q.poll();
            int next = cur + arr[cur];
            if(next < n && next >= 0){
                if(!visited[next]){
                    q.add(next);
                    visited[next] = true;
                }
            }
            next = cur - arr[cur];
            if(next < n && next >= 0){
                if(!visited[next]){
                    q.add(next);
                    visited[next] = true;
                }
            }
        }
        int cnt = 0;
        for(boolean b:visited){
            if(b) cnt++;
        }
        bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
        br.close();
    }
}
