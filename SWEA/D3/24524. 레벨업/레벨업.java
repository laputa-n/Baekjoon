import java.util.Scanner;

public class Solution
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int N = sc.nextInt();
            int[] point = new int[N];
            for(int i = 0; i<N; i++){
                point[i] = sc.nextInt();
            }
            int minDist = Integer.MAX_VALUE;
            for(int i = 1; i < N-1; i++){
                int totalDist = 0;
                int curPoint = point[0];
                for(int j = 1; j<N; j++){
                    if(j == i) continue;
                    totalDist += Math.abs(curPoint - point[j]);
                    curPoint = point[j];
                }
                minDist = Math.min(minDist, totalDist);
            }
            System.out.println(minDist);
        }
    }
}