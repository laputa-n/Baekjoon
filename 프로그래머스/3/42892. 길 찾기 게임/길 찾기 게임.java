import java.util.*;

class Solution {
    static List<Node> tree;
    static int orderIdx;
    public int[][] solution(int[][] nodeinfo) {
        tree = new ArrayList<>();
        for(int i = 0; i<nodeinfo.length; i++){
            tree.add(new Node(nodeinfo[i][0], nodeinfo[i][1], i+1));
        }
        Collections.sort(tree, (n1, n2) -> n2.y - n1.y);
        
        Node root = tree.get(0);
        int nodeCnt = tree.size();
        
        for(int i = 1; i<nodeCnt; i++){
            link(root, tree.get(i));
        }
        
        int[][] answer = new int[2][nodeinfo.length];
        //전위 순회: root - left - right
        //후위 순회: left - right - root;
        orderIdx = 0;
        preOrder(root, answer[0]);
        
        orderIdx = 0;
        postOrder(root, answer[1]);
        
        return answer;
    }
    static void preOrder(Node n, int[] arr){
        if(n != null){
            arr[orderIdx++] = n.num;
            preOrder(n.left, arr);
            preOrder(n.right, arr);
        }
    }
    static void postOrder(Node n, int[] arr){
        if(n != null){
            postOrder(n.left, arr);
            postOrder(n.right, arr);
            arr[orderIdx++] = n.num;
        }
    }
    static void link(Node parent, Node child){
        //왼쪽
        if(parent.x > child.x){
            if(parent.left == null){
                parent.left = child;
            } else {
                link(parent.left, child);
            }
        }
        //오른쪽
        else {
            if(parent.right == null){
                parent.right = child;
            } else {
                link(parent.right, child);
            }
        }
    }
    static class Node {
        int x,y,num;
        Node left, right;
        
        public Node(int x, int y, int num){
            this.x = x;
            this.y = y;
            this.num = num;
        }
        
    }
}