import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    static int find(int x){
        if(x == parent[x])
            return x;

        return parent[x] = find(parent[x]);
    }
    static void union(int a,int b){
        int rootA = find(a);
        int rootB = find(b);
        if(rootA < rootB){
            parent[rootB] = rootA;
        } else {
            parent[rootA] = rootB;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        log[] logs = new log[N+1];
        parent = new int[N+1];
        for(int i = 0; i < N+1; i++){
            parent[i] = i;
        }
        for(int i = 1; i <= N; i++){
            String[] line = br.readLine().split(" ");
            logs[i] = new log(Integer.parseInt(line[0]),Integer.parseInt(line[1]),Integer.parseInt(line[2]));
            for(int j = 1; j<i; j++){
                if(logs[i].x1>logs[j].x2 || logs[i].x2<logs[j].x1) continue;
                union(i,j);
            }
        }
//        for(int i = 1; i <= N; i++){
//            bw.write(String.valueOf(i) + " " + String.valueOf(find(i))+"\n");
//        }
        while(Q-->0){
            String[] line = br.readLine().split(" ");
            int a = Integer.parseInt(line[0]);
            int b = Integer.parseInt(line[1]);
            if(find(a) != find(b)){
                bw.write("0\n");
            } else {
                bw.write("1\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static class log{
        int x1, x2, y;
        public log(int x1, int x2, int y){
            this.x1 = x1;
            this.x2 = x2;
            this.y = y;
        }
    }
}
