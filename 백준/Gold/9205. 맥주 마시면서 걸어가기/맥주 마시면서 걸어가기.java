import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-->0){
            int n = sc.nextInt();
            int[] start = new int[2];
            start[0] = sc.nextInt();
            start[1] = sc.nextInt();
            int[][] store = new int[n][2];
            boolean[] visited = new boolean[n];
            for(int i = 0; i < n; i++){
                store[i][0] = sc.nextInt();
                store[i][1] = sc.nextInt();
            }
            int[] end = new int[2];
            end[0] = sc.nextInt();
            end[1] = sc.nextInt();

            boolean happy = false;
            Queue<int[]> q = new ArrayDeque<>();
            q.offer(start);
            while(!q.isEmpty()){
                int[] cur = q.poll();
                if(dist(cur,end) <= 1000){
                    happy = true;
                    break;
                }

                for(int i = 0; i < n; i++){
                    if(!visited[i] && dist(cur,store[i]) <= 1000){
                        visited[i] = true;
                        q.offer(store[i]);
                    }
                }
            }
            System.out.println(happy?"happy":"sad");
        }
    }
    static int dist(int[] a, int[] b){
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }
}