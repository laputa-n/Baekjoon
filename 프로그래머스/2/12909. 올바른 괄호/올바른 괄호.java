import java.util.*;

class Solution {
    boolean solution(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        
        for(int i = 0; i<s.length(); i++){
            char c = s.charAt(i);
            if(c == '('){
                stack.push(c);
            } else {
                if(stack.isEmpty() || stack.getLast() != '(') return false;
                stack.pop();
            }
        }
        
        if(!stack.isEmpty()) return false;
        return true;
        
    }
}