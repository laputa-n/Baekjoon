class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int video_len_second = toSecond(video_len);
        int pos_second = toSecond(pos);
        int op_start_second = toSecond(op_start);
        int op_end_second = toSecond(op_end);
        if(pos_second >= op_start_second && pos_second <= op_end_second){
            pos_second = op_end_second;
        }

        for(String command:commands){
            if(command.equals("prev")){
                if(pos_second <= 10){
                    pos_second = 0;
                } else {
                    pos_second -= 10;
                }
            } else if(command.equals("next")){
                if(pos_second + 10 >= video_len_second){
                    pos_second = video_len_second;
                } else {
                    pos_second += 10;
                }
            }
            if(pos_second >= op_start_second && pos_second <= op_end_second){
                pos_second = op_end_second;
            }
        }

        return toString(pos_second);
    }

    private int toSecond(String input){
        return Integer.parseInt(input.split(":")[0]) * 60 + Integer.parseInt(input.split(":")[1]);
    }

    private String toString(int second){
        int m = second / 60;
        int s = second % 60;
        return String.format("%02d:%02d", m, s);
    }
}