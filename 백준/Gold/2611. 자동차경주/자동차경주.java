import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        ArrayList<Node>[] adj = new ArrayList[N+1];
        for (int i = 1; i < N+1; i++) {
            adj[i] = new ArrayList<>();
        }
        int[] connected = new int[N+1];
        while(M-->0){
            String[] input = br.readLine().split(" ");
            int p = Integer.parseInt(input[0]);
            int q = Integer.parseInt(input[1]);
            int r = Integer.parseInt(input[2]);
            adj[p].add(new Node(q,r));
            connected[q]++;
        }
        int[] cos = new int[N+1];
        Arrays.fill(cos, -1);
        cos[1] = 0;
        int[] prev = new int[N+1];
        Arrays.fill(prev, -1);
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(1);
        while(!q.isEmpty()){
            int cur = q.poll();
            for(Node n:adj[cur]){
                connected[n.to]--;
                if((cos[cur] + n.cost) > cos[n.to]){
                    cos[n.to] = cos[cur] + n.cost;
                    prev[n.to] = cur;
                }
                if(connected[n.to] == 0 && n.to != 1)
                    q.add(n.to);
            }
        }
        bw.write(String.valueOf(cos[1]) + "\n");
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int x = 1;
        while(true){
            stack.push(x);
            x = prev[x];
            if(x == 1){
                stack.push(1);
                break;
            }
        }
        while(!stack.isEmpty()){
            bw.write(String.valueOf(stack.pop()) + " ");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static class Node{
        int to, cost;
        public Node(int to, int cost){
            this.to = to;
            this.cost = cost;
        }
    }
}