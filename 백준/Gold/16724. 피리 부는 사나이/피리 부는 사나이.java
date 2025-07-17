import java.io.*;
import java.util.*;

public class Main {
    static int[][][] parent;

    static int[] find(int[] p){
        int x = p[0];
        int y = p[1];
        if(parent[x][y][0] == x && parent[x][y][1] == y) return new int[]{x, y};

        parent[x][y] = find(parent[x][y]); // 경로 압축
        return parent[x][y];
    }

    static void union(int[] p, int[] q){
        int[] rootP = find(p);
        int[] rootQ = find(q);
        if(rootP[0] == rootQ[0] && rootP[1] == rootQ[1]) return;

        // 그냥 rootQ를 rootP에 붙임 (크기 순 고려 안함)
        parent[rootQ[0]][rootQ[1]] = rootP;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        parent = new int[N][M][2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                parent[i][j][0] = i;
                parent[i][j][1] = j;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int ni = i, nj = j;
                if (map[i][j] == 'D') ni++;
                else if (map[i][j] == 'U') ni--;
                else if (map[i][j] == 'L') nj--;
                else if (map[i][j] == 'R') nj++;

                union(new int[]{i, j}, new int[]{ni, nj});
            }
        }

        Set<String> cycleRoots = new HashSet<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int[] root = find(new int[]{i, j});
                cycleRoots.add(root[0] + "," + root[1]);
            }
        }

        bw.write(String.valueOf(cycleRoots.size()));
        bw.flush();
        bw.close();
        br.close();
    }
}
