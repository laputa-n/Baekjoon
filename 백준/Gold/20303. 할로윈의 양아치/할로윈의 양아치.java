import java.io.*;
import java.util.*;
public class Main{
    static int[] parent;
    static int find(int x){
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
    static void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);
        if(rootX != rootY) {  // 이 조건만 추가하면 됩니다!
            if(rootX < rootY){
                parent[rootY] = rootX;
            } else {
                parent[rootX] = rootY;
            }
        }
    }
    public static void main(String[] args) throws Exception{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int K = sc.nextInt();
        parent = new int[N+1];
        for(int i = 0; i < N+1; i++){
            parent[i] = i;
        }
        int[] candyCount = new int[N+1];
        for(int i = 1; i <= N; i++){
            candyCount[i] = sc.nextInt();
        }
        while(M-->0){
            int a = sc.nextInt();
            int b = sc.nextInt();
            union(a,b);
        }
        Set<Integer> parentSet = new HashSet<>();
        for(int i = 1; i <= N; i++){
            parent[i] = find(i);
            parentSet.add(parent[i]);
        }

        crew[] crews = new crew[parentSet.size()];
        int idx = 0;
        for(int parentNum: parentSet){
            int thisCrewPeopleCount = 0;
            int thisCrewCandyCount = 0;
            for(int i = 1; i <= N; i++){
                if(parent[i] == parentNum){
                    thisCrewPeopleCount++;
                    thisCrewCandyCount += candyCount[i];
                }
            }
            crews[idx++] = new crew(thisCrewPeopleCount, thisCrewCandyCount);
        }

        int[] dp = new int[K];
        for(crew c: crews){
            if(c.peopleCount >= K || c.peopleCount <= 0) continue;
            for(int i = K-1; i>=c.peopleCount; i--){
                dp[i] = Math.max(dp[i], dp[i - c.peopleCount] + c.candyCount);
            }
        }
        int max = 0;
        for(int i = 0; i < K; i++){
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);

//        bw.flush();
//        bw.close();
//        br.close();
    }
    static class crew {
        int peopleCount, candyCount;
        crew(int peopleCount, int candyCount){
            this.peopleCount = peopleCount;
            this.candyCount = candyCount;
        }
    }
}