import java.io.*;
import java.util.*;
public class Main{
    static int N,M;
    static Map<Integer,Integer> ladders,snakes;
    static int[] cnts = new int[101];
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        ladders = new HashMap<>();
        snakes = new HashMap<>();
        while(N-->0){
            input = br.readLine().split(" ");
            int start = Integer.parseInt(input[0]);
            int end = Integer.parseInt(input[1]);
            ladders.put(start,end);
        }
        while(M-->0){
            input = br.readLine().split(" ");
            int start = Integer.parseInt(input[0]);
            int end = Integer.parseInt(input[1]);
            snakes.put(start,end);
        }
        Arrays.fill(cnts,Integer.MAX_VALUE);
        DFS(1,0);
        bw.write(String.valueOf(cnts[100]));
        bw.flush();
        bw.close();
        br.close();
    }
    static void DFS(int start, int cnt){
        if(start == 100){
            return;
        }
        for(int i = 1; i<=6; i++){
            int next = start + i;
            if(next > 100) continue;
            if(ladders.containsKey(next)){
                next = ladders.get(next);
            }
            if(snakes.containsKey(next)){
                next = snakes.get(next);
            }
            if(cnts[next] > cnt+1){
                cnts[next] = cnt+1;
                DFS(next,cnt+1);
            }
        }
    }
}