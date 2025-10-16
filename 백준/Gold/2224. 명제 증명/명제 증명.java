    import java.io.*;
    import java.util.*;

    public class Main{
        public static void main(String[] args) throws Exception{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            int X = Integer.parseInt(br.readLine());
            boolean[][] T = new boolean[52][52];
            StringTokenizer st;
            while(X-->0){
                st = new StringTokenizer(br.readLine(), " => ");
                int a = convertCharToInt(st.nextToken().charAt(0));
                int b = convertCharToInt(st.nextToken().charAt(0));
                T[a][b] = true;
            }
            for(int k = 0; k<52; k++){
                for(int i = 0; i<52; i++){
                    for(int j = 0; j<52; j++){
                        T[i][j] = T[i][j] ||( T[i][k] && T[k][j]);
                    }
                }
            }
            int cnt = 0;
            for(int i = 0; i<52; i++){
                for(int j = 0; j<52; j++){
                    if(i == j) continue;
                    if(T[i][j]) cnt++;
                }
            }
            bw.write(String.valueOf(cnt));
            for(int i = 0; i<52; i++){
                for(int j = 0; j<52; j++){
                    if(T[i][j]){
                        if(i == j) continue;
                        char a,b;

                        if(i<=25) a = (char) ('A' + i);
                        else a = (char) ('A' + 6 + i);

                        if(j<=25) b = (char) ('A' + j);
                        else b = (char) ('A' + 6 + j);
                        bw.write("\n" + a + " => " + b);
                    }
                }
            }
            bw.flush();
            bw.close();
            br.close();
        }
        private static int convertCharToInt(char c){
            if(c >= 'A' && c <= 'Z') return c - 'A';
            else return c- 'a' + 26;
        }
    }