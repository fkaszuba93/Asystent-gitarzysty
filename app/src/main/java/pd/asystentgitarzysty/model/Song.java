package pd.asystentgitarzysty.model;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import pd.asystentgitarzysty.activity.MainActivity;

import static android.os.Environment.*;

public class Song {

    static final String CHORDS_DIR = "AsystentGitarzysty/chords/";
    static final String LYRICS_DIR = "AsystentGitarzysty/lyrics/";
    static final String TABS_DIR = "AsystentGitarzysty/tabs/";

    private String artist, title;
    private String lyrics, tablature;
    private String filename;
    private Set<Chord> chords;

    public Song(String artist, String title){
        this.artist = artist;
        this.title = title;
        filename = toString() + ".txt";
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public String getLyrics(){
        if (lyrics == null)
            lyrics = getContentFromFile(LYRICS_DIR);
        return lyrics;
    }

    public String getTablature(){
        if (tablature == null)
            tablature = getContentFromFile(TABS_DIR);
        return tablature;
    }

    public Set<Chord> getChords(){
        if (chords == null){
            chords = new HashSet<>();
            String data = getContentFromFile(CHORDS_DIR);
            if (data != null)
                parseChords(data);
            else if (lyrics != null)
                parseChords(lyrics);
        }
        return chords;
    }

    private void parseChords(String source){
        for (String s: source.split("\\s"))
            if (Chord.check(s))
                chords.add(Chord.parse(s));
    }

    private String getContentFromFile(String dir){
        String data = null;
        if (MainActivity.isExternalStorageAvailable()){
            try {
                File file = new File(getExternalStoragePublicDirectory(DIRECTORY_DOCUMENTS),
                        dir + filename);
                data = readFile(file);
            } catch(IOException e){
                throw new RuntimeException(e.getMessage());
            }
        }
        return data;
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
