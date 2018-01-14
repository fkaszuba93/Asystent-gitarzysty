package pd.asystentgitarzysty.model;

import android.os.Environment;
import static android.os.Environment.DIRECTORY_DOCUMENTS;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import pd.asystentgitarzysty.activity.MainActivity;

public class Song {

    public static final Comparator<Song> ARTIST_COMPARATOR = new Comparator<Song>() {
        @Override
        public int compare(Song s1, Song s2) {
            return s1.artist.compareToIgnoreCase(s2.artist);
        }
    };
    public static final Comparator<Song> TITLE_COMPARATOR = new Comparator<Song>() {
        @Override
        public int compare(Song s1, Song s2) {
            return s1.title.compareToIgnoreCase(s2.title);
        }
    };

    private static final String LYRICS_DIR = "AsystentGitarzysty/lyrics/";
    private static final String TABS_DIR = "AsystentGitarzysty/tabs/";

    private String artist, title;
    private String lyrics, tablature;
    private String filename;
    private List<Chord> chords;

    public Song(String artist, String title){
        this.artist = artist;
        this.title = title;
        filename = toString() + ".txt";
        lyrics = getLyrics();
        chords = getChords();
        tablature = getTablature();
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public String getLyrics(){
        if (lyrics == null)
            lyrics = readFile(getFile(LYRICS_DIR));
        return lyrics;
    }

    public String getTablature(){
        if (tablature == null)
            tablature = readFile(getFile(TABS_DIR));
        return tablature;
    }

    public List<Chord> getChords(){
        if (chords == null){
            chords = new ArrayList<>();
            if (lyrics != null)
                parseChords(lyrics);
        }
        return chords;
    }

    private void parseChords(String source){
        for (String s: source.split("\\s")) {
            if (Chord.check(s)) {
                Chord chord = Chord.parse(s);
                if (!chords.contains(chord))
                    chords.add(chord);
            }
        }
    }

    public void deleteContent(){
        File lyricsFile = getFile(LYRICS_DIR);
        File tablatureFile = getFile(TABS_DIR);
        deleteFile(lyricsFile);
        deleteFile(tablatureFile);
    }

    private File getFile(String dir){
        if (MainActivity.isExternalStorageAvailable())
            return new File(
                    Environment.getExternalStoragePublicDirectory(DIRECTORY_DOCUMENTS),
                    dir + filename
            );
        else
            return null;
    }

    private void deleteFile(File file){
        if (file != null && file.exists())
            file.delete();
    }

    private String readFile(File file) {
        if (file.exists()) {
            try {
                FileReader reader = new FileReader(file);
                byte[] buffer = new byte[1048576];
                byte b;
                int read = 0;
                do {
                    b = (byte) reader.read();
                    buffer[read++] = b;
                } while (b != -1);
                reader.close();
                return new String(buffer, 0, read - 1);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        } else
            return null;
    }

    @Override
    public String toString(){
        return artist + " - " + title;
    }
}
