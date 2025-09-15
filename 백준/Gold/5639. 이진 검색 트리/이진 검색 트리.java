import java.io.*;
import java.util.*;
public class Main{
    static Queue<Integer> q = new LinkedList<>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Node first = new Node(Integer.parseInt(br.readLine()));
        while(true){
            String input = br.readLine();
            if(input == null) break;
            int k = Integer.parseInt(input);
            BFS(first,k);
        }
        LeftMidRight(first);
        while(!q.isEmpty()){
            int temp = q.poll();
            bw.write(String.valueOf(temp) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static void LeftMidRight(Node n){
        if(n == null) return;
        LeftMidRight(n.left);
        LeftMidRight(n.right);
        q.add(n.key);
    }
    static void BFS(Node root, int input){
        if(root.key>input){
            if(root.left == null){
                root.left = new Node(input);
                return;
            } else{
                BFS(root.left, input);
            }
        } else {
            if(root.right == null){
                root.right = new Node(input);
                return;
            } else{
                BFS(root.right, input);
            }
        }
    }
    static class Node{
        int key;
        Node left,right;
        Node(int key){
            this.key = key;
        }
    }
}