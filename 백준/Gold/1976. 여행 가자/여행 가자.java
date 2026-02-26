import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    static int find(int x){
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
    static void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);

        if(rootA<rootB){
            parent[rootB] = rootA;
        } else {
            parent[rootA] = rootB;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[] plan = new int[M];
        parent = new int[N];
        for(int i = 0; i < N; i++){
            parent[i] = i;
        }
        for(int i = 0; i<N; i++){
            String[] input = br.readLine().split(" ");
            for(int j = 0; j<N; j++){
                if(input[j].equals("0")) continue;
                if(input[j].equals("1")){
                    union(i, j);
                }
            }
        }
        String[] input = br.readLine().split(" ");
        for(int i = 0; i<M; i++){
            plan[i] = Integer.parseInt(input[i]);
        }
        int c = find(plan[0]-1);
        boolean right = true;
        for(int i = 1; i<M; i++){
            if(find(plan[i]-1) != c){
                right = false;
                break;
            }
        }
        bw.write(right?"YES":"NO");
        bw.flush();
        bw.close();
        br.close();
    }
}
