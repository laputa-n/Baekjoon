import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = br.readLine();

        boolean flag = true;
        int answer = 0;
        int cnt = 1;
        Deque<Character> stack = new ArrayDeque<>();

        for(int i = 0; i < input.length(); i++){
            char c = input.charAt(i);
            if(c == '('){
                stack.push(c);
                cnt *= 2;
            } else if(c == '['){
                stack.push(c);
                cnt *= 3;
            } else{
                if(c == ')'){
                    if(stack.isEmpty() || stack.peek() != '('){
                        flag = false;
                        break;
                    }
                    if(input.charAt(i-1) == '('){
                        answer += cnt;
                    }
                    stack.pop();
                    cnt /= 2;
                } else if(c == ']'){
                    if(stack.isEmpty() || stack.peek() != '['){
                        flag = false;
                        break;
                    } else if(input.charAt(i-1) == '['){
                        answer += cnt;
                    }
                    stack.pop();
                    cnt /= 3;
                }
            }
        }
        if(!flag || !stack.isEmpty()){
            bw.write("0");
        } else {
            bw.write(String.valueOf(answer));
        }
        bw.flush();
        bw.close();
        br.close();
    }
}