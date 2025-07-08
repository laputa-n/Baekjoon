    import java.io.*;
    import java.util.*;

    public class Main {
        static int minDist = Integer.MAX_VALUE;
        static ArrayList<int[]> houseList;
        static ArrayList<int[]> chickenStoreList;
        static int N,M;
        static int[] selectedStore;
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            houseList = new ArrayList<>();
            chickenStoreList = new ArrayList<>();
            selectedStore = new int[M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int f = Integer.parseInt(st.nextToken());
                    if(f == 1){
                        houseList.add(new int[]{i, j});
                    } else if(f == 2){
                        chickenStoreList.add(new int[]{i, j});
                    }
                }
            }
            dfs(0,0);
            bw.write(String.valueOf(minDist));
            bw.flush();
            bw.close();
            br.close();
        }
        static void dfs(int cnt, int idx){
            if(cnt == M){
                calcDist();
                return;
            }
            for(int i = idx; i < chickenStoreList.size(); i++){
                selectedStore[cnt] = i;
                dfs(cnt+1, i+1);
            }
        }
        static void calcDist(){
            int sum = 0;
            for(int[] house: houseList){
                int m = Integer.MAX_VALUE;
                for(int i = 0; i<M; i++){
                    int[] c =  chickenStoreList.get(selectedStore[i]);
                    m = Math.min(m,(Math.abs(c[0] - house[0]) + Math.abs(c[1] - house[1])));
                }
                sum += m;
            }
            minDist = Math.min(minDist, sum);
        }
    }


