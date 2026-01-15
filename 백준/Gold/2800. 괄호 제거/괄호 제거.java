import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String input = br.readLine();
        Deque<Integer> stack = new ArrayDeque<>();
        List<Pair> pairs = new ArrayList<>();
        Set<String> strings = new HashSet<>();
        for(int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if(c == '('){
                stack.push(i);
            } else if(c == ')'){
                pairs.add(new Pair(stack.pop(),i));
            }
        }
        Pair[] pair = new Pair[pairs.size()];
        pairs.toArray(pair);
        for(int i = 1; i< (1<<pair.length); i++){
            List<Integer> tar = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j<pair.length; j++){
                if(0!=(i&(1<<j))){
                    tar.add(pair[j].open);
                    tar.add(pair[j].close);
                }
            }
            if(tar.isEmpty()) continue;
            for(int j = 0; j<input.length(); j++){
                if(!tar.contains(j)){
                    sb.append(input.charAt(j));
                }
            }
            strings.add(sb.toString());
        }
        String[] res = new String[strings.size()];
        strings.toArray(res);
        Arrays.sort(res);
        for(String s : res){
            bw.write(s+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static class Pair{
        int open,close;
        public Pair(int open, int close){
            this.open = open;
            this.close = close;
        }
    }
}
