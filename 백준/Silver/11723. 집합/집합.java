import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int S = 0;
        int M = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            if(cmd.equals("all")){
                S = (1<<21)-1;
            } else if(cmd.equals("empty")){
                S = 0;
            } else{
                int x = Integer.parseInt(st.nextToken());
                if(cmd.equals("add")){
                    S |= (1<<x);
                } else if(cmd.equals("remove")){
                    S &= ~(1<<x);
                } else if(cmd.equals("check")){
                    if((S&(1<<x)) != 0) bw.write("1\n");
                    else bw.write("0\n");
                } else if(cmd.equals("toggle")){
                    S^=(1<<x);
                }
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
