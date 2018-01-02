package pd.asystentgitarzysty.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pd.asystentgitarzysty.R;
import pd.asystentgitarzysty.content.Songs;
import pd.asystentgitarzysty.model.Chord;
import pd.asystentgitarzysty.model.Song;


public class ChordsFragment extends ContentFragment {

    private List<Chord> chords;

    public ChordsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSong(Songs.getCurrentSong());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_chords, container, false);
        contentView = v.findViewById(R.id.chords_view);
        contentText = v.findViewById(R.id.chords_text);
        noContentText = v.findViewById(R.id.no_chords_text);
        isViewCreated = true;
        displayContent();
        return v;
    }

    @Override
    public void setSong(Song song){
        chords = song.getChords();
        if (chords != null && !chords.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Chord chord : chords)
                sb.append(chord.toString() + " ");
            content = sb.toString();
        }
        if (isViewCreated)
            displayContent();
    }
}
