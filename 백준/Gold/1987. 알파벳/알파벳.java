    import java.io.*;
    import java.util.HashSet;
    import java.util.Set;
    import java.util.StringTokenizer;

    public class Main {
        static int[] dx = {-1, 0, 1, 0};
        static int[] dy = {0, 1,0, -1};
        static int R,C;
        static char[][] map;
        static boolean[] visited = new boolean[26];
        static int maxDist = 0;
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            map = new char[R][C];
            for (int i = 0; i < R; i++) {
                map[i] = br.readLine().toCharArray();
            }
            dfs(0,0,1);
            bw.write(String.valueOf(maxDist));
            bw.flush();
            bw.close();
            br.close();
        }
        static void dfs(int row, int col, int depth){
            visited[map[row][col] - 'A'] = true;
            maxDist = Math.max(maxDist, depth);
            for (int i = 0; i < 4; i++) {
                int nextRow = row + dx[i];
                int nextCol = col + dy[i];
                if(nextRow >= 0 && nextRow < R && nextCol >= 0 && nextCol < C){
                    int index = map[nextRow][nextCol] - 'A';
                    if(!visited[index]){
                        dfs(nextRow,nextCol,depth+1);
                    }
                }
            }
            visited[map[row][col] - 'A'] = false;
        }
    }



