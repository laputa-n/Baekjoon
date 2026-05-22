import java.util.*;

class Solution {
    static HashMap<String,String> parent;
    static HashMap<String,Integer> sellAmount;
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        parent = new HashMap<>();
        sellAmount = new HashMap<>();
        
        for(int i = 0; i<enroll.length; i++){
            parent.put(enroll[i], referral[i]);
            sellAmount.put(enroll[i],0);
        }
        
        for(int i = 0; i<seller.length; i++){
            gain(seller[i], amount[i] * 100);
        }
        
        int[] ans = new int[enroll.length];
        for(int i = 0; i<enroll.length; i++){
            ans[i] = sellAmount.get(enroll[i]);
        }
        
        return ans;
        
        
    }
    
    static void gain(String name, int cost){
        int mine;
        int give;
        double cal = cost * 0.1;
        if(cal < 1){
            give = 0;
            mine = cost;
        } else {
            give = (int) cal;
            mine = cost - give;
        }
        
        
        sellAmount.put(name, sellAmount.get(name) + mine);
        
        if(give <1) return;
        String parentName = parent.get(name);
        if(!parentName.equals("-")){
            gain(parentName, give);
        }
    }
}