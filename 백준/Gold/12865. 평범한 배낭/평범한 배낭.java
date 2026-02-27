import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Item[] items = new Item[N+1];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            items[i+1] = new Item(w, v);
        }
        items[0] = new Item(0,0);
        Arrays.sort(items);
        int[][] dp = new int[N+1][K+1];
        for(int i = 1; i<=N; i++){
            for(int j = 1; j<=K; j++){
                if(items[i].weight <= j){
                    dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-items[i].weight]+items[i].value);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        bw.write(String.valueOf(dp[N][K]));
        bw.flush();
        bw.close();
        br.close();
    }
    static class Item implements Comparable<Item>{
        int weight, value;
        public Item(int weight, int value){
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Item o) {
            return Integer.compare(weight, o.weight);
        }
    }
}