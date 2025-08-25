import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        Scanner sc = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<Node> [] graph = new ArrayList[N+1];
        for(int i = 1; i <= N; i++){
            graph[i] = new ArrayList<>();
        }
        while(M-->0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()) * 2;
            graph[a].add(new Node(b,d));
            graph[b].add(new Node(a,d));
        }

        long[] foxTime = new long[N+1];
        Arrays.fill(foxTime, Long.MAX_VALUE);
        foxTime[1] = 0;
        PriorityQueue<Node> pqFox = new PriorityQueue<>();
        pqFox.add(new Node(1,0));
        while(!pqFox.isEmpty()){
            Node cur = pqFox.poll();
            if(foxTime[cur.to]<cur.time) continue;
            for(Node node: graph[cur.to] ){
                long nTime = foxTime[cur.to] + node.time;
                if(foxTime[node.to] > nTime){
                    foxTime[node.to] = nTime;
                    pqFox.add(new Node(node.to, nTime));
                }
            }
        }

        long[][] wolfTime = new long[2][N+1];
        Arrays.fill(wolfTime[0],Long.MAX_VALUE);
        Arrays.fill(wolfTime[1],Long.MAX_VALUE);
        wolfTime[0][1] = 0;
        PriorityQueue<Node> pqWolf = new PriorityQueue<>();
        pqWolf.add(new Node(1,0,0));
        while(!pqWolf.isEmpty()){
            Node cur = pqWolf.poll();
            if(wolfTime[cur.state][cur.to] < cur.time) continue;

            for(Node node: graph[cur.to] ){
                int state = 1 - cur.state;
                long nTime = wolfTime[cur.state][cur.to] + ((cur.state == 0)? node.time / 2 : node.time * 2);
                if(wolfTime[state][node.to] > nTime){
                    wolfTime[state][node.to] = nTime;
                    pqWolf.add(new Node(node.to, nTime, state));
                }
            }
        }
        int cnt = 0;
        for(int i = 2; i <= N; i++){
            if(foxTime[i] < Math.min(wolfTime[0][i],wolfTime[1][i])){
                cnt++;
            }
        }
        bw.write(String.valueOf(cnt));
        
        bw.flush();
        bw.close();
        br.close();
    }
    static class Node implements Comparable<Node>{
        int to;
        long time;
        int state;
        public Node(int to, long  time){
            this.to = to;
            this.time = time;
        }
        public Node(int to, long time, int state){
            this.to = to;
            this.time = time;
            this.state = state;
        }

        @Override
        public int compareTo(Node o){
            return Long.compare(this.time, o.time);
        }
    }
}