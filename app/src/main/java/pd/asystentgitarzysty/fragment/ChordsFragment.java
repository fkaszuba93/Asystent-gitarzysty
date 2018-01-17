package pd.asystentgitarzysty.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import pd.asystentgitarzysty.R;
import pd.asystentgitarzysty.model.Chord;
import pd.asystentgitarzysty.model.Song;


public class ChordsFragment extends ContentFragment {

    private GridLayout grid;
    private List<Chord> chords;

    public ChordsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_chords, container, false);
        contentView = v.findViewById(R.id.chords_view);
        noContentView = v.findViewById(R.id.no_chords_text);
        grid = v.findViewById(R.id.grid);
        return v;
    }

    @Override
    protected boolean isContentAvailable() {
        return chords != null && !chords.isEmpty();
    }

    @Override
    protected void setContent() {
        if (grid.getChildCount() > 0)
            grid.removeAllViews();
        for (Chord chord: chords)
            grid.addView(getChordView(chord));
    }

    private View getChordView(Chord chord){
        View view = getLayoutInflater().inflate(R.layout.chord, null);
        TextView nameText = view.findViewById(R.id.name_text);
        nameText.setText(chord.toString());
        view.setOnClickListener(new ChordViewOnClickListener(chord));
        return view;
    }

    @Override
    public void setSong(Song song){
        chords = song.getChords();
    }


    private class ChordViewOnClickListener implements View.OnClickListener {

        private Chord chord;

        ChordViewOnClickListener(Chord chord) {
            this.chord = chord;
        }

        @Override
        public void onClick(View view) {
            String url = getUrl();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        }

        @NonNull
        private String getUrl() {
            final String URL = "http://www.all-guitar-chords.com/index.php";
            String name = chord.toString();
            String key = chord.getKey();
            Map<String, String> keysMap = Chord.KEYS;
            if (key.contains("#"))
                name = name.replace(key, key + "/" + keysMap.get(key));
            else if (key.contains("b"))
                name = name.replace(key, keysMap.get(key) + "/" + key);
            name = format(name);
            return URL + "?ch=" + name;
        }

        private String format(String s){
            return s.replace("#", "%23")
                    .replace("/", "%2F")
                    .replace("min", "m")
                    .replace("H", "B");
        }
    }
}
