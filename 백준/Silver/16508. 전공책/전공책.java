    import java.io.*;
    import java.util.*;

    public class Main {
        static int[] countLetters(String s){
            int[] count = new int[26];
            for(char c:s.toCharArray()){
                count[c-'A']++;
            }
            return count;
        }
        static boolean canMake(int[] targetCount, int[] totalCount){
            for(int i = 0; i<26; i++){
                if(totalCount[i]<targetCount[i]){
                    return false;
                }
            }
            return true;
        }
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            String T = br.readLine();
            int N = Integer.parseInt(br.readLine());
            int[] costs = new int[N];
            String[] titles = new String[N];
            StringTokenizer st;
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                costs[i] = Integer.parseInt(st.nextToken());
                titles[i] = st.nextToken();
            }
            int minCost = Integer.MAX_VALUE;
            int[] targetCount = countLetters(T);

            for(int mask = 1; mask< (1<<N); mask++){
                int totalCost = 0;
                int[] totalCount = new int[26];

                for(int i = 0; i<N; i++){
                    if((mask&(1<<i)) != 0){
                        totalCost += costs[i];
                        int[] bookCount = countLetters(titles[i]);

                        for(int j = 0; j<26; j++){
                            totalCount[j] += bookCount[j];
                        }
                    }
                }
                if(canMake(targetCount,totalCount)){
                    minCost = Math.min(minCost, totalCost);
                }
            }

            bw.write(minCost==Integer.MAX_VALUE?"-1":String.valueOf(minCost));
            bw.flush();
            bw.close();
            br.close();
        }
    }
