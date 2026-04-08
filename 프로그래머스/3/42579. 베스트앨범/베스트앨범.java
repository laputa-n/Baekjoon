import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays){
        int N = genres.length;
        ArrayList<Music> list = new ArrayList<>();
        for(int i = 0; i<N; i++){
            list.add(new Music(i,plays[i],genres[i]));
        }
        HashMap<String, Integer> genreToPlays = new HashMap<>();
        HashMap<String, PriorityQueue<Music>> genreToMusics = new HashMap<>();

        for(Music m:list){
            genreToPlays.putIfAbsent(m.genre,0);
            genreToPlays.put(m.genre, genreToPlays.get(m.genre) + m.plays);

            genreToMusics.putIfAbsent(m.genre, new PriorityQueue<Music>());
            genreToMusics.get(m.genre).add(m);
        }

        PriorityQueue<Genre> pq = new PriorityQueue<>((g1,g2) -> g2.totalPlays - g1.totalPlays);
        for(String s:genreToPlays.keySet()){
            pq.add(new Genre(s,genreToPlays.get(s)));
        }
        List<Integer> ans = new ArrayList<Integer>();
        while(!pq.isEmpty()){
            Genre g = pq.poll();
            PriorityQueue<Music> p = genreToMusics.get(g.name);
            ans.add(p.poll().id);
            if(!p.isEmpty()) ans.add(p.poll().id);
        }

        int[] re = new int[ans.size()];
        for(int i = 0; i<ans.size(); i++){
            re[i] = ans.get(i);
        }
        
        return re;
    }
    static class Genre {
        String name;
        int totalPlays;
        public Genre(String name, int totalPlays){
            this.name = name;
            this.totalPlays = totalPlays;
        }
    }
    static class Music implements Comparable<Music>{
        int id,plays;
        String genre;
        public Music(int id, int plays, String genre){
            this.id = id;
            this.plays = plays;
            this.genre = genre;
        }

        @Override
        public int compareTo(Music o){
            if(this.plays == o.plays){
                return Integer.compare(this.id, o.id);
            }
            return Integer.compare(o.plays, this.plays);
        }
    }
}