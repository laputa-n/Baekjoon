import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int[][] matrix = new int[N][N];
        for(int i = 0; i<N; i++){
            String[] lines = br.readLine().split(" ");
            for(int j = 0; j<N; j++){
                matrix[i][j] = Integer.parseInt(lines[j]);
            }
        }
        bw.write(String.valueOf(solution(N,L,matrix)));
        bw.flush();
        bw.close();
        br.close();
    }
    static int solution(int N, int L, int[][] matrix){
        int cnt = 0;
        for(int i = 0; i<N; i++){
            boolean[] chk = new boolean[N];
            for(int j = 0; j<N; j++){
                if(j >= N-1){
                    cnt++;
                    break;
                }
                int diff = matrix[i][j] - matrix[i][j+1];
                if(Math.abs(diff)>1) break;
                if(diff < 0){
                    boolean flag = true;
                    for(int k = j; k>j-L; k--){
                        if(k<0){
                            flag = false;
                            break;
                        }
                        if(chk[k] || matrix[i][j] != matrix[i][k]){
                            flag = false;
                            break;
                        }
                    }
                    if(flag){
                        for(int k = j; k>j-L; k--){
                            chk[k] = true;
                        }
                    } else break;
                } else if(diff > 0){
                    boolean flag = true;
                    for(int k = j+1; k<=j+L; k++){
                        if(k>N-1){
                            flag = false;
                            break;
                        }
                        if(chk[k] || matrix[i][j]-1 != matrix[i][k]){
                            flag = false;
                            break;
                        }
                    }
                    if(flag){
                        for(int k = j+1; k<=j+L; k++){
                            chk[k] = true;
                        }
                        j = j+L-1;
                    } else break;
                }
            }
        }

        for(int i = 0; i<N; i++){
            boolean[] chk = new boolean[N];
            for(int j = 0; j<N; j++){
                if(j >= N-1){
                    cnt++;
                    break;
                }
                int diff = matrix[j][i] - matrix[j+1][i];
                if(Math.abs(diff)>1) break;
                if(diff < 0){
                    boolean flag = true;
                    for(int k = j; k>j-L; k--){
                        if(k<0){
                            flag = false;
                            break;
                        }
                        if(chk[k] || matrix[j][i] != matrix[k][i]){
                            flag = false;
                            break;
                        }
                    }
                    if(flag){
                        for(int k = j; k>j-L; k--){
                            chk[k] = true;
                        }
                    } else break;
                } else if(diff > 0){
                    boolean flag = true;
                    for(int k = j+1; k<=j+L; k++){
                        if(k>N-1){
                            flag = false;
                            break;
                        }
                        if(chk[k] || matrix[j][i]-1 != matrix[k][i]){
                            flag = false;
                            break;
                        }
                    }
                    if(flag){
                        for(int k = j+1; k<=j+L; k++){
                            chk[k] = true;
                        }
                        j = j+L-1;
                    } else break;
                }
            }
        }
        return cnt;
    }
}