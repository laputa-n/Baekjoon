import org.w3c.dom.Node;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Main {
    static int[] dRow = {-1,0,1,0};
    static int[] dCol = {0,-1,0,1};
    static char[][] map;
    static int w,h;
    static int ans;
    static int targetBit;
    static void DFS(int curr, int visited, int cost, int[][] dist, int N){
        if(visited == targetBit){
            ans = Math.min(ans,cost);
            return;
        }

        for(int i = 1; i<N; i++){
            if((visited&(1<<(i -1))) == 0 && dist[curr][i] != -1){
                DFS(i,visited | (1<<(i-1)), cost + dist[curr][i], dist, N);
            }
        }
    }
    static int[][] BFS(Point s){
        int[][] dist = new int[h][w];
        for(int i=0; i<h; i++){
            Arrays.fill(dist[i], -1);
        }
        Queue<Point> q = new LinkedList<>();
        q.add(s);
        dist[s.row][s.col] = 0;
        while(!q.isEmpty()){
            Point p = q.poll();
            for(int i =0; i<4; i++){
                int nextRow = p.row + dRow[i];
                int nextCol = p.col + dCol[i];
                if(nextRow < 0 || nextRow >= h || nextCol < 0 || nextCol >= w) continue;
                if(map[nextRow][nextCol] == 'x' || dist[nextRow][nextCol] != -1) continue;
                dist[nextRow][nextCol] = dist[p.row][p.col] + 1;
                q.add(new Point(nextRow, nextCol));
            }
        }
        return dist;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            if(w == 0 && h == 0) break;
            map = new char[h][w];

            List<Point> points = new ArrayList<>();
            for(int i = 0; i < h; i++){
                String line = br.readLine();
                for(int j = 0; j < w; j++){
                    map[i][j] = line.charAt(j);
                    if(map[i][j] == 'o') points.add(0,new Point(i, j));
                    if(map[i][j] == '*') points.add(new Point(i, j));
                }
            }
            int[][] dist = new int[points.size()][points.size()];
            for(int i = 0; i < points.size()-1; i++){
                int[][] temp = BFS(points.get(i));
                for(int j = i; j<points.size(); j++){
                    if(i==j){
                        dist[i][j] = 0;
                        continue;
                    }
                    dist[i][j] = temp[points.get(j).row][points.get(j).col];
                    dist[j][i] = dist[i][j];
                }
            }
            ans = Integer.MAX_VALUE;
            targetBit = (1<<(points.size() -1)) -1;
            DFS(0,0,0,dist,points.size());
            if(ans == Integer.MAX_VALUE){
                bw.write("-1\n");
            } else {
                bw.write(String.valueOf(ans) + "\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static class Point {
        int row,col;
        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
