import java.util.*;

class Solution {
    // d l r u
    static int[] dRow = {1, 0, 0, -1};
    static int[] dCol = {0, -1, 1, 0};
    static char[] dDir = {'d', 'l', 'r', 'u'};

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        int minDis = Math.abs(r - x) + Math.abs(c - y);

        if (minDis > k || (k - minDis) % 2 == 1) {
            return "impossible";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < k; i++) {
            for (int j = 0; j < 4; j++) {
                int nextRow = x + dRow[j];
                int nextCol = y + dCol[j];

                if (nextRow < 1 || nextRow > n || nextCol < 1 || nextCol > m) {
                    continue;
                }

                int remain = k - i - 1;
                int dist = Math.abs(nextRow - r) + Math.abs(nextCol - c);

                if (dist > remain) {
                    continue;
                }

                if ((remain - dist) % 2 == 1) {
                    continue;
                }

                sb.append(dDir[j]);
                x = nextRow;
                y = nextCol;
                break;
            }
        }

        return sb.toString();
    }
}