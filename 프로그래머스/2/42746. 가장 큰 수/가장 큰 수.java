import java.util.*;
class Solution {
    public String solution(int[] numbers) {
        int size = numbers.length;
        String[] input = new String[size];
        for(int i = 0; i<size; i++){
            input[i] = String.valueOf(numbers[i]);
        }
        Arrays.sort(input, (o1,o2) -> (o2+o1).compareTo(o1+o2));
        if(input[0].equals("0")) return "0";
        return String.join("",input);
        
    }
}