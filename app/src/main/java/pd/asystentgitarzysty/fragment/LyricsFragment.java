package pd.asystentgitarzysty.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pd.asystentgitarzysty.R;
import pd.asystentgitarzysty.model.Song;


public class LyricsFragment extends ContentFragment {

    private TextView lyricsText;
    private String lyrics;

    public LyricsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lyrics, container, false);
        contentView = v.findViewById(R.id.lyrics_view);
        noContentView = v.findViewById(R.id.no_lyrics_text);
        lyricsText = v.findViewById(R.id.lyrics_text);
        fullscreenButton = v.findViewById(R.id.fullscreen_button);
        return v;
    }

    @Override
    protected boolean isContentAvailable() {
        return lyrics != null;
    }

    @Override
    protected void setContent() {
        lyricsText.setText(lyrics);
    }

    @Override
    public void setSong(Song song){
        lyrics = song.getLyrics();
    }
}
