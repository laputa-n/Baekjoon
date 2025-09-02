import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        String[] s = br.readLine().split(" ");
        int[] arr = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(s[i]);
        }
        int lp = 0;
        int rp = N-1;
        int min = Integer.MAX_VALUE;
        while(lp<rp){
            int sum = arr[lp]+arr[rp];
            if(Math.abs(sum)<Math.abs(min)){
                min = sum;
            }
            if(sum > 0){
                rp--;
            } else if(sum < 0){
                lp++;
            } else break;
        }
        bw.write(String.valueOf(min));
        bw.flush();
        bw.close();
        br.close();
    }
}