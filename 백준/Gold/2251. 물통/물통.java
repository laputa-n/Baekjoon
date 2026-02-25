import java.io.*;
import java.util.*;

public class Main {
    static int[] limit;
    static boolean[][] checked = new boolean[201][201];
    static Set<Integer> ans = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x1 = Integer.parseInt(st.nextToken());
        int x2 = Integer.parseInt(st.nextToken());
        int x3 = Integer.parseInt(st.nextToken());
        limit = new int[] {x1, x2, x3};

        Queue<Bucket> q = new LinkedList<>();
        q.add(new Bucket(new int[]{0,0,limit[2]}));
        checked[0][0] = true;
        while (!q.isEmpty()) {
            Bucket bucket = q.poll();
            if(bucket.tmp[0] == 0) ans.add(bucket.tmp[2]);
            for(int i = 0; i<3; i++){
                for(int j = 0; j<3; j++){
                    if(i==j) continue;
                    Bucket next = bucket.move(i,j);
                    if(!checked[next.tmp[0]][next.tmp[1]]){
                        checked[next.tmp[0]][next.tmp[1]] = true;
                        q.add(next);
                    }
                }
            }
        }
        List<Integer> res = new ArrayList<>(ans);
        Collections.sort(res);
        for(int i:res){
            bw.write(String.valueOf(i) + " ");
        }
        bw.write("\n");
        bw.flush();
        bw.close();
        br.close();
    }
    static class Bucket{
        int[] tmp;
        public Bucket(int[] t){
            tmp = new int[3];
            for(int i=0;i<3;i++){
                tmp[i] = t[i];
            }
        }
        Bucket move(int from, int to){
            int[] ttmp = {tmp[0],tmp[1],tmp[2]};
            if(tmp[to] + tmp[from] <= limit[to]){
                ttmp[from] = 0;
                ttmp[to] = tmp[to] + tmp[from];
            } else {
                ttmp[from] -= limit[to] - tmp[to];
                ttmp[to] = limit[to];
            }
            return new Bucket(ttmp);
        }
    }
}
