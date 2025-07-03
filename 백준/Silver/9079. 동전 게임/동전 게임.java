import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int[][] tar =
            {{0,1,2},{3,4,5},{6,7,8},
                    {0,3,6},{1,4,7},{2,5,8},
                    {0,4,8},{2,4,6}};
    static boolean[] visited;
    static boolean isSame(int x){
        return x == 0 || x == ((1 << 9) - 1);
    }
    static class Node{
        int x;
        int cnt;
        Node(int x, int cnt){
            this.x = x;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int f = 0;
            visited = new boolean[(1<<9)];
            for(int j = 0; j<3; j++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int k = 0; k<3; k++){
                    String s = st.nextToken();
                    if(s.equals("H"))
                        f |= (1<<(j*3 + k));
                }
            }
            visited[f] = true;
            int minCnt = Integer.MAX_VALUE;
            Queue<Node> q = new LinkedList<>();
            q.add(new Node(f,0));
            while(!q.isEmpty()){
                Node cur = q.poll();
                int curX = cur.x;
                int curCnt = cur.cnt;
                if(isSame(curX)){
                    minCnt = Math.min(minCnt, curCnt);
                }
                for(int j = 0; j<8; j++){
                    int[] dx = tar[j];
                    int mask = 0;
                    for(int k = 0; k<3; k++){
                        mask |= (1<<dx[k]);
                    }
                    int tempX = curX ^ mask;
                    if(!visited[tempX]){
                        visited[tempX] = true;
                        q.add(new Node(tempX,curCnt+1));
                    }
                }
            }
            if (minCnt == Integer.MAX_VALUE) {
                bw.write("-1\n");
            } else {
                bw.write(minCnt + "\n");
            }

        }
        bw.flush();
        bw.close();
        br.close();
    }

}
