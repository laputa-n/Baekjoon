import java.io.*;
import java.util.*;

public class Main {
    static int[] gearwheel = new int[4];
    static void rotateclockwise(int idx){
        if((gearwheel[idx] & 1) > 0){
            gearwheel[idx] >>>= 1;
            gearwheel[idx] |= (1<<7);
        } else
            gearwheel[idx] >>>=1;
    }
    static void rotatecounterclockwise(int idx){
        if((gearwheel[idx] & (1<<7)) > 0){
            gearwheel[idx] <<=1;
            gearwheel[idx] |= 1;
        } else
            gearwheel[idx] <<= 1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 0; i < 4; i++){
            gearwheel[i] = Integer.parseInt(br.readLine(),2);
        }
        int K = Integer.parseInt(br.readLine());
        StringTokenizer st;
        while(K-- > 0){
            st = new StringTokenizer(br.readLine());
            int[] dirList = new int[4];
            int num = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            dirList[num - 1] = dir;
            for(int i = num; i<4; i++){
                int prev = (gearwheel[i-1] & (1<<5)) > 0 ? 1 : 0;
                int next = (gearwheel[i] & (1<<1)) > 0 ? 1 : 0;
                if(prev != next){
                    dirList[i] = -dirList[i-1];
                }  else  break;
            }
            for(int i = num-2; i>=0; i--){
                int prev = (gearwheel[i] & (1<<5)) > 0 ? 1 : 0;
                int next = (gearwheel[i+1] & (1<<1)) > 0 ? 1 : 0;
                if(prev != next){
                    dirList[i] = -dirList[i+1];
                }
            }
            for(int i = 0; i<4; i++){
                if(dirList[i] == 1){
                    rotateclockwise(i);
                } else if(dirList[i] == -1){
                    rotatecounterclockwise(i);
                }
            }
        }
        int totalScore = 0;
        for(int i = 0; i < 4; i++){
            if((gearwheel[i] & (1<<7)) != 0){
                totalScore += 1<<i;
            }
        }
        bw.write(String.valueOf(totalScore));
        bw.flush();
        bw.close();
        br.close();
    }
}
