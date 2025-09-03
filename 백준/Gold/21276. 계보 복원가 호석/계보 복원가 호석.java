import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        String[] people = br.readLine().split(" ");
        Arrays.sort(people);
        Map<String,Integer> index = new HashMap<>();
        for(int i=0;i<N;i++){
            index.put(people[i],i);
        }

        int M = Integer.parseInt(br.readLine());

        int[] edgeCount = new int[N];
        ArrayList<Integer>[] graph = new ArrayList[N];
        ArrayList<Integer>[] afterList = new ArrayList[N];
        for(int i = 0; i < N; i++){
            graph[i] = new ArrayList<>();
            afterList[i] = new ArrayList<>();
        }
        while(M-->0){
            String[] XY = br.readLine().split(" ");
            String X = XY[0];
            String Y = XY[1];
            int xIndex = index.get(X);
            int yIndex = index.get(Y);
            edgeCount[xIndex]++;
            graph[yIndex].add(xIndex);
        }
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> firstRoot = new ArrayList<>();
        for(int i = 0; i < N; i++){
            if(edgeCount[i] == 0){
                firstRoot.add(i);
                queue.offer(i);
            }
        }
        sb.append(firstRoot.size()).append("\n");
        for(int i:firstRoot){
            sb.append(people[i]).append(' ');
        }
        sb.append('\n');
        while(!queue.isEmpty()){
            int x = queue.poll();
            for(int i: graph[x]){
                edgeCount[i]--;
                if(edgeCount[i] == 0){
                    queue.offer(i);
                    afterList[x].add(i);
                }
            }
        }
        for(int i = 0; i < N; i++){
            sb.append(people[i]).append(' ');
            sb.append(afterList[i].size()).append(' ');
            afterList[i].sort(Comparator.naturalOrder());
            for(int j: afterList[i]){
                sb.append(people[j]).append(' ');
            }
            sb.append('\n');
        }
        br.close();
        System.out.println(sb);
    }
}