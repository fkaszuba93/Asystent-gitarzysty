package pd.asystentgitarzysty.content;

import android.os.Environment;
import static android.os.Environment.DIRECTORY_DOCUMENTS;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import pd.asystentgitarzysty.model.Song;

public class Songs {

    private static final String FILE_PATH = "AsystentGitarzysty/songs.json";
    private static List<Song> songsList = getListFromFile();
    private static int currentSong = 0;
    private static boolean listChanged = false;

    public static Song getCurrentSong(){
        return songsList.get(currentSong);
    }

    public static void setCurrentSong(int i){
        currentSong = i;
    }

    public static void add(Song s){
        songsList.add(s);
        listChanged = true;
    }

    public static void delete(Song s){
        songsList.remove(s);
        listChanged = true;
    }

    public static List<Song> getSongsList(){
        return songsList;
    }

    public static boolean isListChanged(){
        return listChanged;
    }

    public static void saveSongsList(){
        try {
            JSONArray array = new JSONArray();
            for (Song s: songsList){
                JSONObject obj = new JSONObject();
                obj.put("artist", s.getArtist());
                obj.put("title", s.getTitle());
                array.put(obj);
            }
            File file = new File(
                    Environment.getExternalStoragePublicDirectory(DIRECTORY_DOCUMENTS),
                    FILE_PATH);
            FileWriter writer = new FileWriter(file);
            writer.write(array.toString());
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static List<Song> getListFromFile(){
        List<Song> list = new ArrayList<>();
        try {
            File file = new File(
                    Environment.getExternalStoragePublicDirectory(DIRECTORY_DOCUMENTS),
                    FILE_PATH);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String data = reader.readLine();
            JSONArray array = new JSONArray(data);
            for (int i = 0; i < array.length(); i++){
                JSONObject obj = array.getJSONObject(i);
                list.add(new Song(obj.getString("artist"), obj.getString("title")));
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return list;
    }
}
