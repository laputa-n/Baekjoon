import org.w3c.dom.Node;

import java.io.*;
    import java.util.PriorityQueue;

    public class Main {
        static int[] parent;
        static int find(int x) {
            if(x == parent[x]) return x;
            return parent[x] = find(parent[x]);
        }
        static boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if(rootX < rootY){
                parent[rootY] = rootX;
                return true;
            }
            else if (rootX > rootY) {
                parent[rootX] = rootY;
                return true;
            } else
                return false;
        }
        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            int N = Integer.parseInt(br.readLine());
            parent = new int[N+1];
            for (int i = 1; i <= N; i++) {
                parent[i] = i;
            }
            int canDonateLength = 0;
            PriorityQueue<Node> pq = new PriorityQueue<>();
            for(int i = 1; i <= N; i++) {
                char[] input = br.readLine().toCharArray();
                for(int j = 0; j < N; j++) {
                    int c = 0;
                    if(input[j] >= 'a' && input[j] <= 'z')
                        c = input[j] - 'a' + 1;
                    else if(input[j] >= 'A' && input[j] <= 'Z')
                        c = input[j] - 'A' + 27;

                    if(i == j+1){
                        canDonateLength += c;
                        continue;
                    }
                    if(c != 0){
                        pq.add(new Node(i,j+1,c));
                    }
                }
            }
            int cnt = 0;
            while(!pq.isEmpty()) {
                if(cnt == N-1) break;
                Node node = pq.poll();
                if(union(node.from, node.to)){
                    cnt++;
                } else {
                    canDonateLength += node.cost;
                }
            }

            boolean connected = true;
            int f = find(1);
            for(int i = 2; i <= N; i++) {
                if(find(i) != f){
                    connected = false;
                    break;
                }
            }
            if(connected){
                while(!pq.isEmpty()) {
                    Node node = pq.poll();
                    canDonateLength += node.cost;
                }
                bw.write(String.valueOf(canDonateLength));
            } else {
                bw.write("-1");
            }
            bw.flush();
            bw.close();
            br.close();
        }

        static class Node implements Comparable<Node> {
            int from,to,cost;
            public Node(int from, int to, int cost) {
                this.from = from;
                this.to = to;
                this.cost = cost;
            }

            public int compareTo(Node o) {
                return Integer.compare(cost, o.cost);
            }
        }

    }