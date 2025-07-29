import java.io.*;
import java.util.*;

public class Main {
    static int[] dRow = {-1,0,1,0};
    static int[] dCol = {0,-1,0,1};
    static int N,M;
    static boolean[][][][] visited;
    static char[][] map;
    static int minCnt = Integer.MAX_VALUE;
    static void BFS(int rsr, int rsc, int bsr, int bsc, int cnt) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{rsr,rsc,bsr,bsc,cnt});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int pCnt = cur[4];
            if(pCnt >= 10){
                return;
            }

            for(int i = 0; i<4; i++){
                int nrr = cur[0];
                int nrc = cur[1];
                int nbr = cur[2];
                int nbc = cur[3];
                while(map[nrr + dRow[i]][nrc + dCol[i]] != '#'){
                    nrr += dRow[i];
                    nrc += dCol[i];
                    if(map[nrr][nrc] == 'O'){
                        break;
                    }
                }

                while(map[nbr + dRow[i]][nbc + dCol[i]] != '#'){
                    nbr += dRow[i];
                    nbc += dCol[i];
                    if(map[nbr][nbc] == 'O'){
                        break;
                    }
                }

                if(map[nbr][nbc] == 'O') continue;

                if(map[nrr][nrc] == 'O'){
                    minCnt = Math.min(minCnt,pCnt + 1);
                }

                if(nrr == nbr && nrc == nbc){
                    int rmd = Math.abs(nrr - cur[0]) + Math.abs(nrc - cur[1]);
                    int bmd = Math.abs(nbr - cur[2]) + Math.abs(nbc - cur[3]);
                    if(rmd > bmd){
                        nrr -= dRow[i];
                        nrc -= dCol[i];
                    } else {
                        nbr -= dRow[i];
                        nbc -= dCol[i];
                    }
                }

                if(!visited[nrr][nrc][nbr][nbc]){
                    visited[nrr][nrc][nbr][nbc] = true;
                    q.add(new int[]{nrr,nrc,nbr,nbc,pCnt+1});
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M][N][M];
        int startRedRow = 0;
        int startRedCol = 0;
        int startBlueRow = 0;
        int startBlueCol = 0;
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if(map[i][j] == 'B'){
                    startBlueRow = i;
                    startBlueCol = j;
                } else if(map[i][j] == 'R'){
                    startRedRow = i;
                    startRedCol = j;
                }
            }
        }
        BFS(startRedRow,startRedCol,startBlueRow,startBlueCol,0);
        if(minCnt == Integer.MAX_VALUE){
            bw.write("-1");
        } else {
            bw.write(String.valueOf(minCnt));
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
