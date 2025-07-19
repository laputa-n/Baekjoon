    import java.io.*;
    import java.util.*;

    public class Main {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            int G = Integer.parseInt(br.readLine());
            List<Integer> list = new ArrayList<>();
            int l = 1;
            int r = 2;
            while(true){
                int diff = r*r - l*l;
                if(r-l == 1 && diff > G)
                    break;
                if(diff < G) r++;
                else if(diff > G) l++;
                else {
                    list.add(r);
                    r++;
                }
            }
            if(list.isEmpty())
                bw.write("-1");
            else {
                for(int val:list){
                    bw.write(String.valueOf(val) + "\n");
                }
            }
            bw.flush();
            bw.close();
            br.close();
        }
    }
