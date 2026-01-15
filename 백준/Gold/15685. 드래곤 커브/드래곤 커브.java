import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        boolean[][] isExistPoint = new boolean[101][101];
        while(N-->0){
            String[] input = br.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);
            int d = Integer.parseInt(input[2]);
            int g = Integer.parseInt(input[3]);
            List<Integer> dirList = new ArrayList<>();
            ArrayDeque<Integer> stack = new ArrayDeque<>();
            dirList.add(d);
            while(g-->0){
                for(Integer i: dirList){
                    stack.push(i);
                }
                while(!stack.isEmpty()){
                    dirList.add((stack.pop()+1) % 4);
                }
            }
            ArrayDeque<int[]> points = new ArrayDeque<>();
            points.push(new int[]{x, y});
            for(Integer i:dirList){
                switch(i){
                    case 0 -> points.push(cmd0(points.peek()));
                    case 1 -> points.push(cmd1(points.peek()));
                    case 2 -> points.push(cmd2(points.peek()));
                    case 3 -> points.push(cmd3(points.peek()));
                }
            }
            while(!points.isEmpty()){
                int[] point = points.pop();
                isExistPoint[point[0]][point[1]] = true;
            }

        }
        int cnt = 0;
        for(int i = 0; i<100; i++){
            for(int j = 0; j<100; j++){
                if(isExistPoint[i][j]){
                    if(isExistPoint[i+1][j] && isExistPoint[i][j+1] && isExistPoint[i+1][j+1]) cnt++;
                }
            }
        }
        bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
        br.close();
    }
    static int[] cmd0(int[] p){
        return new int[]{p[0]+1,p[1]};
    }
    static int[] cmd1(int[] p){
        return new int[]{p[0],p[1]-1};
    }
    static int[] cmd2(int[] p){
        return new int[]{p[0]-1,p[1]};
    }
    static int[] cmd3(int[] p){
        return new int[]{p[0],p[1]+1};
    }
}
