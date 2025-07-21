    import java.io.*;
    import java.util.*;

    public class Main {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            int N = Integer.parseInt(br.readLine());
            int M = Integer.parseInt(br.readLine());
            int[] item = new int[N+1];
            item[N] = 1;
            HashMap<Integer,Integer>[] map = new HashMap[N+1];
            for(int i = 1;i<=N;i++){
                map[i] = new HashMap();
            }
            StringTokenizer st;
            int[] edgeCount = new int[N+1];
            while(M-->0){
                st = new StringTokenizer(br.readLine(), " ");
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                int K = Integer.parseInt(st.nextToken());
                map[X].put(Y,K);
                edgeCount[Y]++;
            }
            List<Integer> basicItem = new ArrayList<>();
            for(int i = 1;i<=N;i++){
                if(map[i].isEmpty()){
                    basicItem.add(i);
                }
            }
            Queue<Integer> q = new LinkedList<>();
            q.add(N);
            while(!q.isEmpty()){
                int b = q.poll();
                if(basicItem.contains(b)) continue;
                int cnt = item[b];
                item[b] = 0;
                for(int k:map[b].keySet()){
                    item[k] += (map[b].get(k)*cnt);
                    edgeCount[k]--;
                    if(edgeCount[k]==0){
                        q.add(k);
                    }
                }
            }
            for(int i: basicItem){
                bw.write(String.valueOf(i) + " " + String.valueOf(item[i]) + "\n");
            }

            bw.flush();
            bw.close();
            br.close();
        }
    }
