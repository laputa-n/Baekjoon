    import java.io.*;
    import java.util.*;
    import java.util.function.DoublePredicate;

    import static java.util.Arrays.asList;

    public class Main {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            int T = Integer.parseInt(br.readLine());
            for(int i = 0; i < T; i++) {
                char[] cmd = br.readLine().toCharArray();
                int n = Integer.parseInt(br.readLine());
                String s = br.readLine();
                Deque<String> dq = new LinkedList<>();
                if(n > 0){
                    String[] nums = s.substring(1,s.length()-1).split(",");
                    dq.addAll(Arrays.asList(nums));
                }
                boolean reverse = false;
                boolean error = false;
                for(char c: cmd){
                    if(c == 'R'){
                        reverse = !reverse;
                    } else if (c == 'D'){
                        if(dq.isEmpty()){
                            error = true;
                            break;
                        }
                        if(reverse){
                            dq.pollLast();
                        } else {
                            dq.pollFirst();
                        }
                    }
                }
                if(error){
                    bw.write("error\n");
                } else {
                    bw.write("[");
                    while(!dq.isEmpty()){
                        bw.write(reverse?dq.pollLast():dq.pollFirst());
                        if(!dq.isEmpty()){
                            bw.write(",");
                        }
                    }
                    bw.write("]\n");
                }
            }
            bw.flush();
            bw.close();
            br.close();
        }
    }


