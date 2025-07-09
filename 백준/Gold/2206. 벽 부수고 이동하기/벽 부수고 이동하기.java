    import java.io.*;
    import java.util.LinkedList;
    import java.util.Queue;
    import java.util.StringTokenizer;

    public class Main {
        static class Node{
            int row;
            int col;
            int dist;
            boolean destroyed;
            Node(int row, int col, int dist, boolean destroyed){
                this.row = row;
                this.col = col;
                this.dist = dist;
                this.destroyed = destroyed;
            }
        }
        static int[] dx = { -1, 0, 1, 0 };
        static int[] dy = { 0, 1, 0, -1 };
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            char[][] matrix = new char[N][M];
            for(int i = 0; i < N; i++) {
                String s = br.readLine();
                for(int j = 0; j < M; j++) {
                    matrix[i][j] = s.charAt(j);
                }
            }
            boolean[][][] visited = new boolean[N][M][2];
            Queue<Node> q = new LinkedList<>();
            q.offer(new Node(0,0,1, false));
            visited[0][0][0] = true;
            while(!q.isEmpty()) {
                Node cur = q.poll();
                if(cur.row == N - 1 && cur.col == M - 1) {
                    bw.write(String.valueOf(cur.dist));
                    break;
                }
                for(int i = 0; i < 4; i++) {
                    int nextRow = cur.row + dx[i];
                    int nextCol = cur.col + dy[i];
                    if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) {
                        continue;
                    }
                    int nextDist = cur.dist + 1;
                    if(matrix[nextRow][nextCol] == '0') {
                        if(cur.destroyed && !visited[nextRow][nextCol][1]) {
                            visited[nextRow][nextCol][1] = true;
                            q.offer(new Node(nextRow, nextCol, nextDist, true));
                        } else if(!cur.destroyed && !visited[nextRow][nextCol][0]) {
                            visited[nextRow][nextCol][0] = true;
                            q.offer(new Node(nextRow, nextCol, nextDist, false));
                        }
                    } else if(matrix[nextRow][nextCol] == '1') {
                        if(!cur.destroyed){
                            visited[nextRow][nextCol][1] = true;
                            q.offer(new Node(nextRow, nextCol, nextDist, true));
                        }
                    }
                }
            }
            if(!visited[N-1][M-1][0] && !visited[N-1][M-1][1]) {
                bw.write("-1");
            }
            bw.flush();
            bw.close();
            br.close();
        }
    }



