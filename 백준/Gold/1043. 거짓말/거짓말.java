import javax.print.attribute.standard.ReferenceUriSchemesSupported;
import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    static int find(int x) {
        if(x==parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
    static void union(int a, int b) {
        int rootX = find(a);
        int rootY = find(b);

        if(rootX<rootY) parent[rootY] = rootX;
        else parent[rootX] = rootY;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N+1];
        for(int i = 0; i<=N; i++){
            parent[i] = i;
        }
        List<Integer>[] parties = new List[M];
        for(int i = 0; i < M; i++){
            parties[i] = new ArrayList<>();
        }

        String[] input = br.readLine().split(" ");
        for(int i = 1; i<input.length; i++){
            union(Integer.parseInt(input[i]),0);
        }
        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            for(int j = 0; j<n; j++){
                int x = Integer.parseInt(st.nextToken());
                parties[i].add(x);
            }
            if(n==1) continue;
            int c = parties[i].get(0);
            for(int p: parties[i]){
                union(c,p);
            }
        }
        int cnt = 0;
        for(int i = 0; i<M; i++){
            boolean known = false;
            for(int j = 0; j<parties[i].size(); j++){
                if(find(parties[i].get(j)) == 0){
                    known = true;
                    break;
                }
            }
            if(!known) cnt++;
        }
        bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
        br.close();
    }
}
