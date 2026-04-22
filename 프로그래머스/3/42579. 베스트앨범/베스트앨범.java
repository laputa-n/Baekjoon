import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays){
        int size = genres.length;
        
        HashMap<String, Integer> genreToPlays = new HashMap<>();
        HashMap<String, List<Integer>> genreToIndex = new HashMap<>();
        
        for(int i = 0; i<size; i++){
            genreToPlays.put(genres[i], genreToPlays.getOrDefault(genres[i],0)+plays[i]);
            
            genreToIndex.putIfAbsent(genres[i],new ArrayList<Integer>());
            genreToIndex.get(genres[i]).add(i);
        }
        
        List<String> gens = new ArrayList<>(genreToPlays.keySet());
        gens.sort( (g1, g2) -> {
            return genreToPlays.get(g2) - genreToPlays.get(g1);
        });
        
        List<Integer> ans = new ArrayList<>();
        
        for(String g:gens){
            genreToIndex.get(g).sort((o1,o2) -> {
                if(plays[o1] == plays[o2]){
                    return o1-o2;
                }
                return plays[o2] - plays[o1];
                });    
            
            ans.add(genreToIndex.get(g).get(0));
            if(genreToIndex.get(g).size()>=2) ans.add(genreToIndex.get(g).get(1));
        }
        
        int[] result = new int[ans.size()];
        for(int i = 0; i<result.length; i++){
            result[i] = ans.get(i);
        }
        
        return result;
    }
}