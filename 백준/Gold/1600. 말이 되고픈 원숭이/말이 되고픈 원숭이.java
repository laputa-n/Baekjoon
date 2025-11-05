import java.io.*;
import java.util.*;

public class Main {
    static int[] horseRowMove = {-2, -1, 1, 2, -2, -1, 1, 2};
    static int[] horseColMove = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] dRow = {-1, 0, 1, 0};
    static int[] dCol = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int K = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        int W = Integer.parseInt(input[0]);
        int H = Integer.parseInt(input[1]);
        int[][] matrix = new int[H][W];
        for (int i = 0; i < H; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < W; j++) {
                matrix[i][j] = Integer.parseInt(input[j]);
            }
        }
        boolean[][][] dp = new boolean[H][W][K + 1];
        dp[0][0][0] = true;
        ArrayDeque<Node> pq = new ArrayDeque<>();
        pq.offer(new Node(0, 0, 0, 0));
        int answer = -1;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (node.row == H - 1 && node.col == W - 1) {
                answer = node.moveCount;
                break;
            }
            // normalMove
            for (int i = 0; i < 4; i++) {
                int nextRow = node.row + dRow[i];
                int nextCol = node.col + dCol[i];
                if (nextRow < 0 || nextRow >= H || nextCol < 0 || nextCol >= W) continue;
                if (dp[nextRow][nextCol][node.horseMoveCount] || matrix[nextRow][nextCol] == 1) continue;
                dp[nextRow][nextCol][node.horseMoveCount] = true;
                pq.offer(new Node(nextRow, nextCol, node.horseMoveCount, node.moveCount + 1));
            }

            for (int i = 0; i < 8; i++) {
                if (node.horseMoveCount + 1 > K) continue;
                int nextRow = node.row + horseRowMove[i];
                int nextCol = node.col + horseColMove[i];
                if (nextRow < 0 || nextRow >= H || nextCol < 0 || nextCol >= W) continue;
                if (dp[nextRow][nextCol][node.horseMoveCount + 1] || matrix[nextRow][nextCol] == 1) continue;
                dp[nextRow][nextCol][node.horseMoveCount + 1] = true;
                pq.offer(new Node(nextRow, nextCol, node.horseMoveCount + 1, node.moveCount + 1));
            }
        }
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    static class Node {
        int row, col, horseMoveCount, moveCount;

        public Node(int row, int col, int horseMoveCount, int moveCount) {
            this.row = row;
            this.col = col;
            this.horseMoveCount = horseMoveCount;
            this.moveCount = moveCount;
        }
    }
}