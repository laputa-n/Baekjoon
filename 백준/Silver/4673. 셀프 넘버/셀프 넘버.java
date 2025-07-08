import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] cnt = new int[10001];
        for(int i = 1; i<=10000; i++){
            int x = d(i);
            while(x<=10000){
                cnt[x]++;
                x = d(x);
            }
        }
        for(int i = 1; i<= 10000; i++){
            if(cnt[i]==0){
                bw.write(String.valueOf(i) + "\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();

    }
    static int d(int x){
        int sum = x;
        int temp1 = x%10;
        int temp2 = x/10;
        sum += temp1;
        while(temp2 != 0){
            temp1 = temp2%10;
            sum += temp1;
            temp2 = temp2/10;
        }
        return sum;
    }
}
