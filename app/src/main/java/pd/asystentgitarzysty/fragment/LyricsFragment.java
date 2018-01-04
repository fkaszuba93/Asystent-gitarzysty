package pd.asystentgitarzysty.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pd.asystentgitarzysty.R;
import pd.asystentgitarzysty.model.Song;


public class LyricsFragment extends ContentFragment {

    public LyricsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lyrics, container, false);
        contentView = v.findViewById(R.id.lyrics_view);
        contentText = v.findViewById(R.id.lyrics_text);
        noContentText = v.findViewById(R.id.no_lyrics_text);
        return v;
    }

    @Override
    public void setSong(Song song){
        content = song.getLyrics();
        displayContent();
    }
}
