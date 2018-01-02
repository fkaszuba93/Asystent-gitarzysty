package pd.asystentgitarzysty.content;

import java.util.ArrayList;
import java.util.List;

import pd.asystentgitarzysty.model.Song;

public class Songs {

    private static List<Song> songsList = createList();
    private static int currentSong = 0;

    private static List<Song> createList(){
        List<Song> list = new ArrayList<>();
        list.add(new Song("qwerty", "test"));
        list.add(new Song("Jimi Hendrix", "Little Wing"));
        return list;
    }

    public static Song getCurrentSong(){
        return songsList.get(currentSong);
    }

    public static void setCurrentSong(int i){
        currentSong = i;
    }

    public static void add(Song s){
        songsList.add(s);
    }

    public static List<Song> getSongsList(){
        return songsList;
    }
}
