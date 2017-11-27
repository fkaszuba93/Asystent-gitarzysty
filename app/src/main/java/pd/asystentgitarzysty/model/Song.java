package pd.asystentgitarzysty.model;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import pd.asystentgitarzysty.activity.MainActivity;

import static android.os.Environment.*;

public class Song {

    private String artist, title, lyricsFilename, tablatureFilename;
    static final String CHORDS_DIR = "AsystentGitarzysty/chords/";
    static final String LYRICS_DIR = "AsystentGitarzysty/lyrics/";
    static final String TABS_DIR = "AsystentGitarzysty/tabs/";

    public Song(String artist, String title){
        this.artist = artist;
        this.title = title;
//        lyricsFilename = artist + " - " + title + ".txt";
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public String getLyricsFromFile(){
        String lyrics = null;
        if (MainActivity.isExternalStorageAvailable()){
            try {
                File file = new File(getExternalStoragePublicDirectory(DIRECTORY_DOCUMENTS),
                        LYRICS_DIR + title + ".txt");
                lyrics = readFile(file);
            } catch(IOException e){
                throw new RuntimeException(e.getMessage());
            }
        }
        return lyrics;
    }

    private String readFile(File file) throws IOException {
        if (file.exists()){
            FileReader reader = new FileReader(file);
            byte[] buffer = new byte[1048576];
            byte b;
            int read = 0;
            do {
                b = (byte) reader.read();
                buffer[read++] = b;
            } while(b != -1);
            reader.close();
            return new String(buffer, 0, read - 1);
        } else {
            return null;
        }
    }

    @Override
    public String toString(){
        return artist + " - " + title;
    }
}
