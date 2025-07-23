import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[][] stat = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                stat[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int diffMin = Integer.MAX_VALUE;
        for(int i = 1; i< (1<<N)/2; i++){
            List<Integer> star = new ArrayList<>();
            List<Integer> link = new ArrayList<>();
            for(int j = 0; j < N; j++){
                if((i&1<<j) != 0){
                    star.add(j);
                } else {
                    link.add(j);
                }
            }
            int starPower = 0;
            int linkPower = 0;
            for(int j = 0; j<star.size(); j++){
                int num1 = star.get(j);
                for(int k = j+1; k<star.size(); k++){
                    int num2 = star.get(k);
                    starPower += (stat[num1][num2] + stat[num2][num1]);
                }
            }
            for(int j = 0; j<link.size(); j++){
                int num1 = link.get(j);
                for(int k = j+1; k<link.size(); k++){
                    int num2 = link.get(k);
                    linkPower += (stat[num1][num2] + stat[num2][num1]);
                }
            }
            diffMin = Math.min(diffMin,Math.abs(starPower - linkPower));
            if(diffMin == 0)
                break;
        }
        bw.write(String.valueOf(diffMin));
        bw.flush();
        bw.close();
        br.close();
    }
}
