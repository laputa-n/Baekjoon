import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, Integer> cnt = new HashMap<>();
        HashMap<String,HashMap<Integer,Integer>> genreNumPlay = new HashMap<>();
        for(int i = 0; i<genres.length; i++) {
            if(!cnt.containsKey(genres[i])){
                HashMap<Integer,Integer> numPlay = new HashMap<>();
                cnt.put(genres[i],plays[i]);
                numPlay.put(i,plays[i]);
                genreNumPlay.put(genres[i],numPlay);
            } else {
                cnt.put(genres[i],cnt.get(genres[i]) + plays[i]);
                genreNumPlay.get(genres[i]).put(i,plays[i]);
            }
        }

        List<String> keySet = new ArrayList<>(cnt.keySet());

        keySet.sort((s1, s2) -> Integer.compare(cnt.get(s2), cnt.get(s1)));

        ArrayList<Integer> list = new ArrayList<>();
        for(String key: keySet){
            HashMap<Integer,Integer> numPlay = genreNumPlay.get(key);
            List<Integer> musicNum = new ArrayList<>(numPlay.keySet());

            musicNum.sort((s1, s2) -> Integer.compare(numPlay.get(s2), numPlay.get(s1)));
            list.add(musicNum.get(0));
            musicNum.remove(0);
            if(!musicNum.isEmpty())
                list.add(musicNum.get(0));
        }
        return list.stream().mapToInt(i -> i).toArray();
    }
}