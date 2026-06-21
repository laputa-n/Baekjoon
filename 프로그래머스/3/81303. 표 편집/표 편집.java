import java.util.*;

class Solution {
    static ArrayDeque<int[]> stack;
    static int[] prev;
    static int[] next;
    //n = 표의 행 개수, k = 현재 인덱스, cmd = 명령어
    public String solution(int n, int k, String[] cmd) {
        stack = new ArrayDeque<>();
        prev = new int[n];
        next = new int[n];
        for(int i = 0; i<n; i++){
            prev[i] = i-1;
            next[i] = i+1;
        }
        next[n-1] = -1;
        
        char[] answer = new char[n];
        Arrays.fill(answer, 'O');
        
        for(String s: cmd){
            switch(s.charAt(0)){
                case 'U':{
                    int count = Integer.parseInt(s.substring(2));
                    while(count-->0){
                        k = prev[k];
                    }
                    break;
                }
                case 'D':{
                    int count = Integer.parseInt(s.substring(2));
                    while(count-->0){
                        k = next[k];
                    }
                    break;
                }
                //삭제
                case 'C':{
                    int prevRow = prev[k];
                    int nextRow = next[k];
                    answer[k] = 'X';
                    
                    stack.push(new int[]{k,prevRow,nextRow});
                    
                    // 보통의 경우에는
                    // next[prevRow] = nextRow;
                    // prev[nextRow] = prevRow;
                    
                    //삭제할 칸이 맨 위인 경우에는
                    //prev[nextRow] = prevRow 만 하고
                    //삭제할 칸이 맨 뒤인 경우에는
                    // next[prevRow] = nextRow 만 한다
                    
                    if(prevRow != -1){
                        next[prevRow] = nextRow;
                    }
                    
                    //삭제할 칸이 맨 뒤가 아닌 경우
                    if(nextRow != -1){
                        prev[nextRow] = prevRow;
                    }
                    
                    k = nextRow == -1? prevRow : nextRow;
                    break;
                }
                case 'Z':{
                    int[] task = stack.pop();
                    int restoreRow = task[0];
                    int prevRow = task[1];
                    int nextRow = task[2];
                    
                    answer[restoreRow] = 'O';
                    
                    //기본으로는
                    //next[prevRow] = restoreRow;
                    //prev[nextRow] = restoreRow; 해줘야 함
                    if(prevRow != -1){
                        next[prevRow] = restoreRow;
                    }
                    
                    if(nextRow != -1){
                        prev[nextRow] = restoreRow;
                    }
                    
                    prev[restoreRow] = prevRow;
                    next[restoreRow] = nextRow;
                    break;
                }
            }
        }
        
        return  new String(answer);
        
    }
}