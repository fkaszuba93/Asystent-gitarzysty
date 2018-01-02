package pd.asystentgitarzysty.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pd.asystentgitarzysty.R;
import pd.asystentgitarzysty.activity.MainActivity;
import pd.asystentgitarzysty.model.Chord;
import pd.asystentgitarzysty.model.Song;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChordsFragment extends Fragment {

    private TextView chordsText, noChordsText;
    private View chordsView;
    private List<Chord> chords;
    private boolean isViewCreated = false;

    public ChordsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        MainActivity activity = (MainActivity) context;
        chords = activity.getCurrentSong().getChords();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_chords, container, false);
        chordsView = v.findViewById(R.id.chords_view);
        chordsText = v.findViewById(R.id.chords_text);
        noChordsText = v.findViewById(R.id.no_chords_text);
        isViewCreated = true;
        displayChords();
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isViewCreated = false;
    }

    private void displayChords(){
        if (chords != null && !chords.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Chord chord : chords)
                sb.append(chord.toString() + " ");
            chordsText.setText(sb.toString());
            chordsView.setVisibility(View.VISIBLE);
            noChordsText.setVisibility(View.GONE);
        } else {
            chordsView.setVisibility(View.GONE);
            noChordsText.setVisibility(View.VISIBLE);
        }
    }

    public void setSong(Song song){
        chords = song.getChords();
        if (isViewCreated)
            displayChords();
    }
}
