package pd.asystentgitarzysty.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pd.asystentgitarzysty.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LyricsFragment extends Fragment {

    private TextView lyricsText, noLyricsText;
    private View lyricsView;

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

        String l = "qwertyuiop\n" +
                "asdfghjkl\n" +
                "zxcvbnm\n";
        lyricsText.setText(l);
        lyricsView.setVisibility(View.VISIBLE);
        noLyricsText.setVisibility(View.GONE);

        return v;
    }
}
