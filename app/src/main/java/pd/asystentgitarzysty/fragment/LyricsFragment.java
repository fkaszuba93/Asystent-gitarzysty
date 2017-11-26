package pd.asystentgitarzysty.fragment;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import static android.os.Environment.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import pd.asystentgitarzysty.R;
import pd.asystentgitarzysty.activity.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class LyricsFragment extends Fragment {

    private TextView lyricsText, noLyricsText;
    private View lyricsView;
    static final String DIR = "AsystentGitarzysty/lyrics";

    public LyricsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lyrics, container, false);
        lyricsText = v.findViewById(R.id.lyrics_text);
        noLyricsText = v.findViewById(R.id.no_lyrics_text);
        lyricsView = v.findViewById(R.id.lyrics_view);
        displayLyrics(getLyricsFromFile());
        return v;
    }

    private void displayLyrics(String lyrics){
        if (lyrics != null) {
            lyricsText.setText(lyrics);
            lyricsView.setVisibility(View.VISIBLE);
            noLyricsText.setVisibility(View.GONE);
        }
    }

    private String getLyricsFromFile(){
        String lyrics = null;
        if (MainActivity.isExternalStorageAvailable()){
            try {
                File file = new File(getExternalStoragePublicDirectory(DIRECTORY_DOCUMENTS),
                        DIR + "/lyrics.txt");
                FileReader reader = new FileReader(file);
                byte[] buffer = new byte[1048576];
                byte b;
                int read = 0;
                do {
                    b = (byte) reader.read();
                    buffer[read++] = b;
                } while(b != -1);
                reader.close();

                lyrics = new String(buffer, 0, read - 1);
            } catch(IOException e){
                throw new RuntimeException(e.getMessage());
            }
        }
        return lyrics;
    }
}
