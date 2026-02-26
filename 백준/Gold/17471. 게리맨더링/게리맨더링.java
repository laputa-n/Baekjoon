import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] people= new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i<=N; i++){
            people[i] = Integer.parseInt(st.nextToken());
        }
        List<Integer>[] adj = new List[N+1];
        for(int i = 1; i<=N; i++){
            adj[i] = new ArrayList<>();
        }
        for(int i = 1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            for(int j = 0; j<a; j++){
                adj[i].add(Integer.parseInt(st.nextToken()));
            }
        }
        int diff = 10000;
        for(int i = 1; i<((1<<N)-1); i++){
            List<Integer> red = new ArrayList<>();
            List<Integer> blue = new ArrayList<>();
            for(int j = 0; j<N; j++){
                if(((1<<j)&i)!=0){
                    red.add(j+1);
                }
            }
            for(int j = 1; j<=N; j++){
                if(!red.contains(j)){
                    blue.add(j);
                }
            }

            boolean[] visited = new boolean[N+1];
            Queue<Integer> queue = new LinkedList<>();
            queue.add(red.get(0));
            visited[red.get(0)] = true;
            while(!queue.isEmpty()){
                int cur = queue.poll();
                for(int n:adj[cur]){
                    if(!visited[n] && red.contains(n)){
                        visited[n] = true;
                        queue.add(n);
                    }
                }
            }
            boolean isConnected = true;
            for(int n:red){
                if(!visited[n]){
                    isConnected = false;
                    break;
                }
            }
            if(!isConnected) continue;

            queue = new LinkedList<>();
            queue.add(blue.get(0));
            visited[blue.get(0)] = true;
            while(!queue.isEmpty()){
                int cur = queue.poll();
                for(int n:adj[cur]){
                    if(!visited[n] && blue.contains(n)){
                        visited[n] = true;
                        queue.add(n);
                    }
                }
            }

            isConnected = true;
            for(int n:blue){
                if(!visited[n]){
                    isConnected = false;
                    break;
                }
            }
            if(!isConnected) continue;


            int redPeopleCnt = 0;
            int bluePeopleCnt = 0;
            for(int n:red){
                redPeopleCnt+=people[n];
            }
            for(int n:blue){
                bluePeopleCnt+=people[n];
            }
            diff= Math.min(diff,Math.abs(redPeopleCnt - bluePeopleCnt));
            if(diff == 0) break;
        }
        bw.write(diff==10000?"-1":String.valueOf(diff)+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
