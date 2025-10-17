    import java.io.*;
    import java.util.*;

    public class Main{
        static int[] parent;
        static int find(int x){
            if(x == parent[x]) return x;
            return parent[x] = find(parent[x]);
        }
        static void union(int a, int b){
            int rootA = find(a);
            int rootB = find(b);
            if(rootA < rootB) parent[rootB] = rootA;
            else parent[rootA] = rootB;
        }

        public static void main(String[] args) throws Exception{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            int N = Integer.parseInt(br.readLine());
            parent = new int[N+1];
            for(int i = 1; i <= N; i++){
                parent[i] = i;
            }
            StringTokenizer st;
            for(int i =0; i<N-2; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a,b);
            }
            int x = find(1);
            int y = -1;
            for(int i = 2; i <= N; i++){
                int p = find(i);
                if(x != p){
                    y = p;
                    break;
                }
            }
            bw.write(String.valueOf(x) + " " + String.valueOf(y));
            bw.flush();
            bw.close();
            br.close();
        }
    }