import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] person1Answers = new int[]{1,2,3,4,5};
        int[] person2Answers = new int[]{2,1,2,3,2,4,2,5};
        int[] person3Answers = new int[]{3,3,1,1,2,2,4,4,5,5};
        int person1length = person1Answers.length;
        int person2length = person2Answers.length;
        int person3length = person3Answers.length;
        int person1Cnt = 0;
        int person2Cnt = 0;
        int person3Cnt = 0;
        for (int i = 0; i < answers.length; i++) {
            if(answers[i] == person1Answers[i%person1length]) person1Cnt++;
            if(answers[i] == person2Answers[i%person2length]) person2Cnt++;
            if(answers[i] == person3Answers[i%person3length]) person3Cnt++;
        }
        int maxCnt = Math.max(Math.max(person1Cnt,person2Cnt),person3Cnt);
        List<Integer> winningPersonNum = new ArrayList<>();
        if(maxCnt == person1Cnt) winningPersonNum.add(1);
        if(maxCnt == person2Cnt) winningPersonNum.add(2);
        if(maxCnt == person3Cnt) winningPersonNum.add(3);

        return winningPersonNum.stream().mapToInt(Integer::intValue).toArray();
    }
}