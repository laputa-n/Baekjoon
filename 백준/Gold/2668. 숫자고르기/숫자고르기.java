import java.io.*;
import java.util.*;

public class Main {
    static int N,num;
    static int[] arr;
    static boolean[] visited;
    static List<Integer> answer = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        visited = new boolean[N+1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        for(int i = 1; i<=N; i++){
            visited[i] = true;
            num = i;
            DFS(i);
            visited[i] = false;
        }
        Collections.sort(answer);
        bw.write(String.valueOf(answer.size() + "\n"));
        for(int a:answer){
            bw.write(String.valueOf(a) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static void DFS(int i){
        if(arr[i] == num){
            answer.add(num);
            return;
        }
        if(!visited[arr[i]]){
            visited[arr[i]] = true;
            DFS(arr[i]);
            visited[arr[i]] = false;
        }
    }
}