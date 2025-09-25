import java.io.*;
import java.util.StringTokenizer;

public class Main{
    static int jhMoney, smMoney;
    static int jhHas = 0;
    static int smHas = 0;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int c = Integer.parseInt(br.readLine());
        jhMoney = smMoney = c;
        int[] price = new int[14];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i =0; i<14; i++){
            price[i] = Integer.parseInt(st.nextToken());
        }
        int[] plus = new int[14];
        int[] minus = new int[14];
        for(int i = 1; i<14; i++){
            if(price[i] < price[i-1]){
                plus[i] = 0;
                minus[i] = minus[i-1]+1;
            } else if(price[i] > price[i-1]) {
                minus[i] = 0;
                plus[i] = plus[i-1]+1;
            } else {
                plus[i] = 0;
                minus[i] = 0;
            }
        }

        for(int i =0; i<14; i++){
            jhHas += jhMoney/price[i];
            jhMoney %= price[i];

            if(minus[i] >= 3){
                smHas += smMoney/price[i];
                smMoney %= price[i];
            }

            if(plus[i] >= 3){
                smMoney += (smHas * price[i]);
                smHas = 0;
            }
        }

        int jhResult = jhHas*price[13] + jhMoney;
        int smResult = smHas*price[13] + smMoney;

        if(jhResult == smResult) bw.write("SAMESAME");
        else if(smResult > jhResult) bw.write("TIMING");
        else bw.write("BNP");
        bw.flush();
        bw.close();
        br.close();
    }
}