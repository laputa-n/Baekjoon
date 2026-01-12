import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());          // 도시 수
        int R = Integer.parseInt(st.nextToken()) * 2;      // 내일로 티켓 가격 (2배 스케일)

        HashMap<String, Integer> map = new HashMap<>();
        int idx = 0;
        for (String s : br.readLine().split(" ")) {
            map.put(s, idx++);
        }

        int M = Integer.parseInt(br.readLine());           // 여행할 도시 수
        String[] trip = br.readLine().split(" ");

        int[][] price1 = new int[N][N]; // 일반
        int[][] price2 = new int[N][N]; // 내일로 적용

        Set<String> free = new HashSet<>(Arrays.asList("Mugunghwa", "ITX-Saemaeul", "ITX-Cheongchun"));
        Set<String> half = new HashSet<>(Arrays.asList("S-Train", "V-Train"));

        for (int i = 0; i < N; i++) {
            Arrays.fill(price1[i], INF);
            Arrays.fill(price2[i], INF);
            price1[i][i] = 0;
            price2[i][i] = 0;
        }

        int K = Integer.parseInt(br.readLine()); // 교통 수단 수
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            String type = st.nextToken();
            int from = map.get(st.nextToken());
            int to = map.get(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            int base = 2 * cost; // 일반요금 (2배 스케일)
            price1[from][to] = price1[to][from] = Math.min(price1[from][to], base);

            int disc;
            if (free.contains(type)) disc = 0;
            else if (half.contains(type)) disc = cost; // (base/2) == cost (2배 스케일 기준)
            else disc = base;

            price2[from][to] = price2[to][from] = Math.min(price2[from][to], disc);
        }

        // Floyd-Warshall
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                if (price1[i][k] == INF && price2[i][k] == INF) continue;
                for (int j = 0; j < N; j++) {
                    if (price1[i][k] != INF && price1[k][j] != INF) {
                        price1[i][j] = Math.min(price1[i][j], price1[i][k] + price1[k][j]);
                    }
                    if (price2[i][k] != INF && price2[k][j] != INF) {
                        price2[i][j] = Math.min(price2[i][j], price2[i][k] + price2[k][j]);
                    }
                }
            }
        }

        int res1 = 0;
        int res2 = R; // 내일로는 티켓값부터 포함

        for (int i = 0; i < M - 1; i++) {
            int a = map.get(trip[i]);
            int b = map.get(trip[i + 1]);
            res1 += price1[a][b];
            res2 += price2[a][b];
        }

        bw.write(res2 < res1 ? "Yes" : "No");
        bw.flush();
    }
}
