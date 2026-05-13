import java.util.*;
class Solution {
    static int[] dRow = {-1,-1,0,1,1,1,0,-1};
    static int[] dCol = {0,1,1,1,0,-1,-1,-1};
    
    public int solution(int[] arrows) {
        Node cur = new Node(0,0);
        
        Map<Node, List<Node>> visited = new HashMap<>();
        
        int cnt = 0;
        for(int d: arrows){
            for(int i = 0; i<2; i++){
                Node nextNode = new Node(cur.row + dRow[d], cur.col + dCol[d]);
                
                if(!visited.containsKey(nextNode)){
                    visited.put(nextNode, makeEdgeList(cur));
                    
                    if(!visited.containsKey(cur)){
                        visited.put(cur, makeEdgeList(nextNode));
                    } else {
                        visited.get(cur).add(nextNode);
                    }
                } else if(visited.containsKey(nextNode) && !visited.get(cur).contains(nextNode)){
                    visited.get(nextNode).add(cur);
                    visited.get(cur).add(nextNode);
                    cnt++;
                }
                cur = nextNode;
                    
            }
        }
        
        return cnt;
    }
    
    
    static ArrayList<Node> makeEdgeList(Node n){
        ArrayList<Node> edge = new ArrayList<>();
        edge.add(n);
        return edge;
    }
    static class Node {
        int row, col;
        
        public Node(int r, int c){
            this.row = r;
            this.col = c;
        }
        
        @Override
        public boolean equals(Object o){
            if(this == o) return true;
            if(o == null || getClass() != o.getClass()) return false;
            
            Node node = (Node) o;
            return row == node.row && col == node.col;
        }
        @Override
        public int hashCode(){
            return Objects.hash(row, col);
        }
    }
    
}