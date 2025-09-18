import org.w3c.dom.Node;

import java.util.*;

public class Main{
    static int N,K;
    static int[] time = new int[100001];
    static int[] parent = new int[100001];
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        Arrays.fill(time, Integer.MAX_VALUE);
        BFS();
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(K);
        int i = K;
        while(i != N){
            stack.push(parent[i]);
            i = parent[i];
        }
        StringBuilder sb = new StringBuilder();
        sb.append(time[K]).append("\n");
        while(!stack.isEmpty()){
            sb.append(stack.pop()).append(" ");
        }
        System.out.println(sb.toString());
    }
    static void BFS(){
        Queue<Integer> q = new LinkedList<>();
        q.add(N);
        parent[N] = -1;
        time[N] = 0;
        while(!q.isEmpty()){
            int cur = q.poll();
            if(cur == K){
                return;
            }
            int[] next = {cur+1,cur-1,cur*2};
            for(int i:next){
                if(i >= 0 && i <= 100000){
                    if(time[i] > time[cur]){
                        time[i] = time[cur] + 1;
                        q.add(i);
                        parent[i] = cur;
                    }
                }
            }
        }
    }
}