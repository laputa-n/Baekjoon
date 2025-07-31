import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer> [] lst;
    static int N;
    static int cnt = 0;
    static int r;
    static void DFS(int idx){
        if(lst[idx].contains(r)){
            lst[idx].remove(Integer.valueOf(r));
        }
        if(lst[idx].isEmpty()){
            cnt++;
            return;
        }
        for(int j : lst[idx]){
            DFS(j);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        lst = new ArrayList[N];
        for(int i = 0; i < N; i++){
            lst[i] = new ArrayList<>();
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int gp = 0;
        for(int i = 0; i < N; i++){
            int p = Integer.parseInt(st.nextToken());
            if(p == -1){
                gp = i;
                continue;
            }
            lst[p].add(i);
        }
        r = Integer.parseInt(br.readLine());
        if( r == gp)
            bw.write("0");
        else {
            DFS(gp);
            bw.write(String.valueOf(cnt));
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
