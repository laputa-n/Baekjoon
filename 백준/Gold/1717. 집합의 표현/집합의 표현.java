import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    static int find(int x){
        if(x==parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
    static boolean union(int a, int b){
        int rootX = find(a);
        int rootY = find(b);
        if(rootX == rootY) return false;

        if(rootX<rootY){
            parent[rootY] = rootX;
        } else {
            parent[rootX] = rootY;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        parent = new int[n+1];
        for(int i=1;i<=n;i++){
            parent[i] = i;
        }
        String[] line;
        while(m-->0){
            line = br.readLine().split(" ");
            int x = Integer.parseInt(line[0]);
            int y = Integer.parseInt(line[1]);
            int z = Integer.parseInt(line[2]);
            if(x == 0){
                union(y,z);
            } else {
                if(find(y) == find(z)){
                    sb.append("YES\n");
                } else {
                    sb.append("NO\n");
                }
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
