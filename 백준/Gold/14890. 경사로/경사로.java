import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];
        boolean[][][] checked = new boolean[N][N][2];
        for(int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }
        int cnt = 0;
        for(int i = 0; i < N; i++) {
            boolean isPass = true;
            for(int j = 0; j < N-1; j++) {
                if(isPass){
                    if(map[i][j+1] > map[i][j]){
                        if(j-L+1<0){
                            isPass = false;
                            break;
                        }
                        for(int k = 0; k<L; k++){
                            if(map[i][j-k] != (map[i][j+1] - 1) || checked[i][j-k][0]){
                                isPass = false;
                                break;
                            }
                        }
                        for(int k = 0; k<L; k++){
                            checked[i][j-k][0] = true;
                        }
                    } else if(map[i][j+1] < map[i][j]) {
                        if(j+L > N-1){
                            isPass = false;
                            break;
                        }
                        for(int k = 0; k<L; k++){
                            if(map[i][j+k+1]!= (map[i][j]-1) || checked[i][j+k+1][0]){
                                isPass = false;
                                break;
                            }
                        }
                        for(int k = 0; k<L; k++){
                            checked[i][j+k+1][0] = true;
                        }
                        j+=(L-1);
                    }
                } else break;
            }
            if(isPass) cnt++;
        }

        for(int i = 0; i < N; i++) {
            boolean isPass = true;
            for(int j = 0; j < N-1; j++) {
                if(isPass){
                    if(map[j+1][i] > map[j][i]){
                        if(j-L+1<0){
                            isPass = false;
                            break;
                        }
                        for(int k = 0; k<L; k++){
                            if(map[j-k][i] != (map[j+1][i] - 1) || checked[j-k][i][1]){
                                isPass = false;
                                break;
                            }
                        }
                        for(int k = 0; k<L; k++){
                            checked[j-k][i][1] = true;
                        }
                    } else if(map[j+1][i] < map[j][i]) {
                        if(j+L > N-1){
                            isPass = false;
                            break;
                        }
                        for(int k = 0; k<L; k++){
                            if(map[j+k+1][i]!= (map[j][i]-1) || checked[j+k+1][i][1]){
                                isPass = false;
                                break;
                            }
                        }
                        for(int k = 0; k<L; k++){
                            checked[j+k+1][i][1] = true;
                        }
                        j+=(L-1);
                    }
                } else break;
            }
            if(isPass) cnt++;
        }

        bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
        br.close();

    }
}