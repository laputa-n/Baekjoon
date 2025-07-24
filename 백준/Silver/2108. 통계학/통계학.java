import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int sum = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            sum += arr[i];
            if(map.containsKey(arr[i])){
                map.put(arr[i], map.get(arr[i])+1);
            } else {
                map.put(arr[i], 1);
            }
        }
        int mode = 0;
        for(int val: map.values()){
            mode = Math.max(mode, val);
        }
        List<Integer> lst = new ArrayList<>();
        for(int key: map.keySet()){
            if(map.get(key) == mode){
                lst.add(key);
            }
        }
        lst.sort(Integer::compareTo);
        Arrays.sort(arr);
        bw.write(String.valueOf(Math.round((float)sum / N)) + "\n"); //1
        bw.write(String.valueOf(arr[N/2]) + "\n"); //2
        bw.write(lst.size() > 1? String.valueOf(lst.get(1)) + "\n":String.valueOf(lst.get(0)) + "\n");
        bw.write(String.valueOf(arr[N-1] - arr[0])); //4
        bw.flush();
        bw.close();
        br.close();
    }
}
