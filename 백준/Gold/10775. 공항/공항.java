import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    static int find(int r){
        if(r == parent[r]) return r;
        return parent[r] = find(parent[r]);
    }
    static boolean union(int a,int b){
        a = find(a);
        b = find(b);
        if(a == b) return false;
        if(a<b) parent[b] = a;
        else parent[a] = b;
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());
        parent = new int[G+1];
        for(int i = 0; i < G+1; i++){
            parent[i] = i;
        }
        int cnt = 0;
        for(int i = 0 ; i<P; i++){
            int g = Integer.parseInt(br.readLine());
            int emptyGate = find(g);
            if(emptyGate == 0){
                break;
            }
            cnt++;
            union(emptyGate,emptyGate-1);
        }
        bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
        br.close();
    }
}


