import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] input = br.readLine().split(" ");
        int R = Integer.parseInt(input[0]);
        int C = Integer.parseInt(input[1]);
        int M = Integer.parseInt(input[2]);
        HashMap<Integer, Shark> idxToShark = new HashMap<>();
        HashMap<Integer, int[]> numToMove = new HashMap<>();
        numToMove.put(1, new int[]{-1,0});
        numToMove.put(2, new int[]{1,0});
        numToMove.put(3, new int[]{0,1});
        numToMove.put(4, new int[]{0,-1});
        for(int i = 1; i <= M; i++) {
            input = br.readLine().split(" ");
            int r = Integer.parseInt(input[0]);
            int c = Integer.parseInt(input[1]);
            int s = Integer.parseInt(input[2]);
            int d = Integer.parseInt(input[3]);
            int z = Integer.parseInt(input[4]);
            if(d == 1 || d == 2){
                s %= (2*(R-1));
            } else {
                s %= (2*(C-1));
            }
            idxToShark.put(i, new Shark(r, c, s, d, z));
        }
        int loc = 0;
        int catchSize = 0;
        while(loc < C){
            loc++;
            int minRow = Integer.MAX_VALUE;
            int tarIdx = -1;
            for(int i:idxToShark.keySet()){
                if(idxToShark.get(i).col == loc){
                    if(idxToShark.get(i).row < minRow){
                        minRow = idxToShark.get(i).row;
                        tarIdx = i;
                    }
                }
            }
            if(tarIdx != -1){
                catchSize += idxToShark.get(tarIdx).size;
                idxToShark.remove(tarIdx);
            }
            int[][] board = new int[R+1][C+1];
            for(int i:new ArrayList<>(idxToShark.keySet())){
                Shark s = idxToShark.get(i);
                int curDir = s.direction;

                for(int j = 0; j<s.speed; j++){
                    if(curDir == 1){
                        if(s.row == 1)
                            curDir = 2;
                    } else if(curDir == 2){
                        if(s.row == R)
                            curDir = 1;
                    } else if(curDir == 3){
                        if(s.col == C)
                            curDir = 4;
                    } else if(curDir == 4){
                        if(s.col == 1)
                            curDir = 3;
                    }
                    s.row += numToMove.get(curDir)[0];
                    s.col += numToMove.get(curDir)[1];
                }
                s.direction = curDir;
                if(board[s.row][s.col] != 0){
                    if(idxToShark.get(board[s.row][s.col]).size > s.size){
                        idxToShark.remove(i);
                    } else {
                        idxToShark.remove(board[s.row][s.col]);
                        board[s.row][s.col] = i;
                    }
                } else {
                    board[s.row][s.col] = i;
                }
            }
        }
        bw.write(String.valueOf(catchSize));
        bw.flush();
        bw.close();
        br.close();
    }
    static class Shark{
        int row,col,speed,direction,size;
        public Shark(int row,int col,int speed,int direction,int size){
            this.row = row;
            this.col = col;
            this.speed = speed;
            this.direction = direction;
            this.size = size;
        }
    }
}
