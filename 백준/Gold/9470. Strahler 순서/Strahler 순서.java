    import java.io.*;
    import java.util.*;

    public class Main {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            int T = Integer.parseInt(br.readLine());
            StringTokenizer st;
            while(T-- > 0) {
                st = new StringTokenizer(br.readLine());
                int K = Integer.parseInt(st.nextToken());
                int M = Integer.parseInt(st.nextToken());
                int P = Integer.parseInt(st.nextToken());
                int[] edgeList = new int[M+1];
                int[] seq = new int[M+1];
                ArrayList<Integer>[] prevList = new ArrayList[M+1];
                ArrayList<Integer>[] nextList = new ArrayList[M+1];
                for(int i = 1; i <= M; i++) {
                    prevList[i] = new ArrayList<>();
                    nextList[i] = new ArrayList<>();
                }
                while(P-->0){
                    st = new StringTokenizer(br.readLine());
                    int A = Integer.parseInt(st.nextToken());
                    int B = Integer.parseInt(st.nextToken());
                    nextList[A].add(B);
                    prevList[B].add(A);
                    edgeList[B]++;
                }
                Queue<Integer> q = new LinkedList<>();
                for(int i = 1; i <= M; i++) {
                    if(edgeList[i] == 0){
                        q.add(i);
                        seq[i] = 1;
                    }
                }
                while(!q.isEmpty()){
                    int cur = q.poll();
                    int max = 0;
                    for(int prev: prevList[cur]){
                        max = Math.max(max, seq[prev]);
                    }
                    int cnt = 0;
                    for(int prev: prevList[cur]){
                        if(seq[prev] == max){
                            cnt++;
                        }
                    }
                    if(cnt>=2)
                        seq[cur] = max+1;
                    else if(cnt == 1)
                        seq[cur] = max;
                    for(int n: nextList[cur]){
                        edgeList[n]--;
                        if(edgeList[n] == 0){
                            q.add(n);
                        }
                    }
                }
                bw.write(String.valueOf(K) + " " + String.valueOf(seq[M]) + "\n");
            }
            bw.flush();
            bw.close();
            br.close();
        }
    }
