import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        int start = sc.nextInt();
        int[] prev = new int[start+1];
        int[] cnt = new int[start+1];
        Arrays.fill(cnt, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        prev[start] = start;
        cnt[start] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        while(!q.isEmpty()){
            int cur = q.poll();
            if(cur == 1) break;

            List<Integer> next = new ArrayList<>();
            if(cur%3 == 0) next.add(cur/3);
            if(cur%2 == 0) next.add(cur/2);
            next.add(cur-1);

            for(Integer i:next){
                if(cnt[i]>cnt[cur]+1){
                    cnt[i] = cnt[cur]+1;
                    prev[i] = cur;
                    q.add(i);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(cnt[1]).append("\n");
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        int p = 1;
        while(p!=start){
            p = prev[p];
            stack.push(p);
        }
        while(!stack.isEmpty()){
            sb.append(stack.pop()).append(" ");
        }
        System.out.println(sb.toString());
    }
}