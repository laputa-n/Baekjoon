import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int idx = 0;
        HashMap<String,Integer> nameToIndex = new HashMap<>();
        String ancestor = br.readLine();
        nameToIndex.put(ancestor,idx++);
        HashMap<Integer,Double> indexToBloodRate = new HashMap<>();
        indexToBloodRate.put(nameToIndex.get(ancestor),1.0);
        HashMap<Integer,List<String>> indexToChildren = new HashMap<>();
        HashMap<Integer, Integer> indexToBeforeCount = new HashMap<>();
        indexToBeforeCount.put(0,0);
        while(N-->0){
            String[] line = br.readLine().split(" ");
            String child = line[0];
            String parent = line[1];
            String parent2 = line[2];
            if(!nameToIndex.containsKey(child)){
                nameToIndex.put(child,idx++);
                indexToBloodRate.put(nameToIndex.get(child),0.0);
            }
            if(!nameToIndex.containsKey(parent)){
                nameToIndex.put(parent,idx++);
                indexToBloodRate.put(nameToIndex.get(parent),0.0);
            }
            if(!nameToIndex.containsKey(parent2)){
                nameToIndex.put(parent2,idx++);
                indexToBloodRate.put(nameToIndex.get(parent2),0.0);
            }
            indexToChildren.putIfAbsent(nameToIndex.get(parent),new ArrayList<>());
            indexToChildren.putIfAbsent(nameToIndex.get(parent2),new ArrayList<>());
            indexToChildren.get(nameToIndex.get(parent)).add(child);
            indexToChildren.get(nameToIndex.get(parent2)).add(child);
            indexToBeforeCount.putIfAbsent(nameToIndex.get(child),2);
        }

        Queue<Integer> pq = new LinkedList<>();
        pq.add(0);
        for(int i:nameToIndex.values()){
            if(!indexToBeforeCount.containsKey(i)){
                indexToBeforeCount.put(i,0);
                pq.add(i);
            }
        }
        while(!pq.isEmpty()){
            int cur = pq.poll();
            double curBloodRate = indexToBloodRate.get(cur);
            List<String> children = indexToChildren.get(cur);
            if (children == null) continue;
            for(String child:children){
                int childIdx = nameToIndex.get(child);
                indexToBloodRate.put(childIdx,indexToBloodRate.get(childIdx) + curBloodRate/2);
                indexToBeforeCount.put(childIdx,indexToBeforeCount.get(childIdx)-1);
                if(indexToBeforeCount.get(childIdx) == 0) pq.add(childIdx);
            }
        }
        double maxBloodRate = -1;
        String maxChild = "";
        while (M-- > 0) {
            String rep = br.readLine();
            double rate = nameToIndex.containsKey(rep)
                    ? indexToBloodRate.get(nameToIndex.get(rep))
                    : 0.0;

            if (rate > maxBloodRate) {
                maxBloodRate = rate;
                maxChild = rep;
            }
        }
        bw.write(maxChild);
        bw.flush();
        bw.close();
        br.close();
    }
}
