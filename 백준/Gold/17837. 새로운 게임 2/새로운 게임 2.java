import java.io.*;
import java.util.*;

public class Main{
    static int[] dRow = {0,0,-1,1};
    static int[] dCol = {1,-1,0,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];
        for(int i = 0; i<N; i++){
            String[] input = br.readLine().split(" ");
            for(int j = 0; j<N; j++){
                map[i][j] = Integer.parseInt(input[j]);
            }
        }
        List<Integer>[][] matrix = new List[N][N];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                matrix[i][j] = new ArrayList<Integer>();
            }
        }
        // 행, 열, 방향
        int[][] horse = new int[K][3];
        for(int i = 0; i<K; i++){
            String[] input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            int c = Integer.parseInt(input[2]);
            horse[i][0] = a-1;
            horse[i][1] = b-1;
            horse[i][2] = c-1;
            matrix[a-1][b-1].add(i);
        }

        int cnt = 0;
        boolean over4 = false;
        while(cnt < 1000 && !over4){
            cnt++;
            for(int i=0; i<K; i++){
                int curRow = horse[i][0];
                int curCol = horse[i][1];
                int curDir = horse[i][2];

                int nextRow = curRow + dRow[curDir];
                int nextCol = curCol + dCol[curDir];
                //체스판 벗어나거나 파란색이면
                if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N || map[nextRow][nextCol] == 2){
                    //방향 반대로 바꾸고
                    curDir = curDir%2==0? curDir+1 : curDir-1;
                    horse[i][2] = curDir;
                    //갈 칸 탐색
                    nextRow = curRow + dRow[curDir];
                    nextCol = curCol + dCol[curDir];
                    //방향 바꾸고 갈 칸도 벗어나거나 파란색이면 그대로
                    if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N || map[nextRow][nextCol] == 2){
                        continue;
                    } else {
                        //빨간색이거나 하얀색이면
                        int curIdx = matrix[curRow][curCol].indexOf(i);
                        int size = matrix[curRow][curCol].size();
                        List<Integer> tar = new ArrayList<>(matrix[curRow][curCol].subList(curIdx,size));
                        matrix[curRow][curCol].subList(curIdx,size).clear();
                        if(map[nextRow][nextCol] == 1){
                            Collections.reverse(tar);
                        }
                        for(int t:tar){
                            matrix[nextRow][nextCol].add(t);
                            horse[t][0] = nextRow;
                            horse[t][1] = nextCol;
                        }
                        if(matrix[nextRow][nextCol].size()>=4){
                            over4 = true;
                            break;
                        }
                    }
                }
                else
                //하양,빨간색이면
                {
                    int curIdx = matrix[curRow][curCol].indexOf(i);
                    int size = matrix[curRow][curCol].size();
                    List<Integer> tar = new ArrayList<>(matrix[curRow][curCol].subList(curIdx,size));
                    matrix[curRow][curCol].subList(curIdx,size).clear();

                    if(map[nextRow][nextCol] == 1) Collections.reverse(tar);
                    for(int t:tar){
                        matrix[nextRow][nextCol].add(t);
                        horse[t][0] = nextRow;
                        horse[t][1] = nextCol;
                    }
                    if(matrix[nextRow][nextCol].size()>=4){
                        over4 = true;
                        break;
                    }
                }
            }
        }
        if(!over4) bw.write("-1");
        else bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
        br.close();
    }
}