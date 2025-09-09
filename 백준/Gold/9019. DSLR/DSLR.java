import java.io.*;
import java.util.*;
public class Main{
    static int start,target;
    static boolean[] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while(T-->0){
            String[] input = br.readLine().split(" ");
            start = Integer.parseInt(input[0]);
            target = Integer.parseInt(input[1]);
            visited = new boolean[10000];
            visited[start] = true;
            Queue<Register> q = new LinkedList<>();
            q.offer(new Register(start,""));
            while(!q.isEmpty()){
                Register r = q.poll();
                if(r.num == target){
                    bw.write(r.buf + "\n");
                    break;
                }
                if(!visited[r.D()]){
                    visited[r.D()] = true;
                    q.offer(new Register(r.D(), r.buf+"D"));
                }
                if(!visited[r.S()]){
                    visited[r.S()] = true;
                    q.offer(new Register(r.S(), r.buf+"S"));
                }
                if(!visited[r.L()]){
                    visited[r.L()] = true;
                    q.offer(new Register(r.L(), r.buf+"L"));
                }
                if(!visited[r.R()]){
                    visited[r.R()] = true;
                    q.offer(new Register(r.R(), r.buf+"R"));
                }
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static class Register{
        int num;
        String buf;
        public Register(int num, String buf){
            this.num = num;
            this.buf = buf;
        }
        int D(){
            return (num*2)%10000;
        }
        int S(){
            return num == 0? 9999 : num - 1;
        }
        int L(){
            return num % 1000 * 10 + num / 1000;
        }
        int R(){
            return num % 10 * 1000 + num / 10;
        }
    }
}