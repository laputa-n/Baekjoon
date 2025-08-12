import java.io.*;
import java.util.*;
public class Main{
    static int[] parent;
    static int find(int x){
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
    static void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);
        if(rootX < rootY){
            parent[rootY] = rootX;
        } else {
            parent[rootX] = rootY;
        }
    }
    public static void main(String[] args) throws Exception{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while(T-- > 0){
            int N = sc.nextInt();
            parent = new int[N];
            for(int i = 0; i < N; i++){
                parent[i] = i;
            }
            camp[] camps = new camp[N];
            for(int i = 0; i < N; i++){
                int x = sc.nextInt();
                int y = sc.nextInt();
                int r = sc.nextInt();
                camps[i] = new camp(x,y,r);
            }
            for(int i = 0; i < N; i++){
                for(int j = i+1; j < N; j++){
                    if(Math.pow(camps[i].row - camps[j].row,2) + Math.pow(camps[i].col - camps[j].col,2) <= Math.pow(camps[i].range + camps[j].range,2)){
                        union(i,j);
                    }
                }
            }
            Set<Integer> set = new HashSet<>();
            for(int i = 0; i < N; i++){
                set.add(find(i));
            }
            sb.append(set.size()).append("\n");
        }
        System.out.println(sb);
//        bw.flush();
//        bw.close();
//        br.close();
    }
    static class camp{
        int row, col, range;
        camp(int row, int col, int range){
            this.row = row;
            this.col = col;
            this.range = range;
        }
    }
}