import java.io.*;
import java.util.*;

public class Main {
    static int N,R;
    static List<Integer>[] edges;
    static Integer[] childCnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        R = Integer.parseInt(input[1]);;
        int Q = Integer.parseInt(input[2]);;
        edges = new ArrayList[N+1];
        for(int i = 1; i <= N; i++)
            edges[i] = new ArrayList<>();
        childCnt = new Integer[N+1];
        for(int i = 0; i<N-1; i++){
            input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            edges[a].add(b);
            edges[b].add(a);
        }
        Arrays.fill(childCnt, 1);
        DFS(R, -1);
        while(Q-->0){
            int q= Integer.parseInt(br.readLine());
            bw.write(String.valueOf(childCnt[q]));
            if(Q!=0) bw.write("\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static void DFS(int i,int prev){
        for(int n:edges[i]){
            if(prev!=n){
                DFS(n,i);
            }
        }
        if(prev != -1){
            childCnt[prev]+=childCnt[i];
        }
    }
}