import java.io.*;

public class Main{
    static int[] choice;
    static boolean[] visited, done;
    static int cnt;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while(T-->0){
            int n = Integer.parseInt(br.readLine());
            choice = new int[n+1];
            visited = new boolean[n+1];
            done = new boolean[n+1];
            String[] input = br.readLine().split(" ");
            for(int i = 0; i < n; i++){
                choice[i+1] = Integer.parseInt(input[i]);
            }
            cnt = 0;
            for(int i = 1; i<=n; i++){
                if(done[i]) continue;
                dfs(i);
            }
            bw.write(String.valueOf(n-cnt) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static void dfs(int i){
        if(done[i]) return;

        if(visited[i]){
            done[i] = true;
            cnt++;
        }
        visited[i] = true;
        dfs(choice[i]);
        done[i] = true;
        visited[i] = false;

    }
}