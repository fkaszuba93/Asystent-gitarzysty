package pd.asystentgitarzysty.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pd.asystentgitarzysty.R;
import pd.asystentgitarzysty.activity.MainActivity;
import pd.asystentgitarzysty.model.Song;

/**
 * A simple {@link Fragment} subclass.
 */
public class LyricsFragment extends Fragment {

    private TextView lyricsText, noLyricsText;
    private View lyricsView;
    private MainActivity activity;
    private Song song;

    public LyricsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
        song = activity.getCurrentSong();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lyrics, container, false);
        lyricsText = v.findViewById(R.id.lyrics_text);
        noLyricsText = v.findViewById(R.id.no_lyrics_text);
        lyricsView = v.findViewById(R.id.lyrics_view);
        displayLyrics(song.getLyrics());
        return v;
    }

    private void displayLyrics(String lyrics){
        if (lyrics != null) {
            lyricsText.setText(lyrics);
            lyricsView.setVisibility(View.VISIBLE);
            noLyricsText.setVisibility(View.GONE);
        } else {
            lyricsView.setVisibility(View.GONE);
            noLyricsText.setVisibility(View.VISIBLE);
        }
    }

    public void setSong(Song song){
        this.song = song;
        displayLyrics(song.getLyrics());
    }
}
