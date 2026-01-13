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
        if(rootA == rootB) return;
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
        int M = Integer.parseInt(st.nextToken());
        parent = new int[N+1];
        for(int i = 0; i < N+1; i++){
            parent[i] = i;
        }
        PriorityQueue<edge> pq1 = new PriorityQueue<>();
        PriorityQueue<edge> pq2 = new PriorityQueue<>();

        for(int i = 0; i < M+1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq1.add(new edge(a,b,c));
            pq2.add(new edge(a,b,c == 1?0:1));
        }
        int cnt = 0;
        while(!pq1.isEmpty()){
            edge e = pq1.poll();
            int a = e.a;
            int b = e.b;
            if(find(a) != find(b)){
                union(a,b);
                if(e.c == 0)
                    cnt++;
            }
        }
        for(int i = 0; i < N+1; i++){
            parent[i] = i;
        }
        int cnt2 = 0;
        while(!pq2.isEmpty()){
            edge e = pq2.poll();
            int a = e.a;
            int b = e.b;
            if(find(a) != find(b)){
                union(a,b);
                if(e.c == 1)
                    cnt2++;
            }
        }
        bw.write(String.valueOf((cnt+cnt2)*(cnt-cnt2)));
        bw.flush();
        bw.close();
        br.close();
    }
    static class edge implements Comparable<edge>{
        int a,b,c;
        public edge(int a,int b,int c){
            this.a = a;
            this.b = b;
            this.c = c;
        }
        public int compareTo(edge e){
            return Integer.compare(this.c,e.c);
        }
    }
}
