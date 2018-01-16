package pd.asystentgitarzysty.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pd.asystentgitarzysty.R;
import pd.asystentgitarzysty.model.Song;


public class TablatureFragment extends ContentFragment {

    private TextView tablatureText;
    private String tablature;

    public TablatureFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tablature, container, false);
        contentView = v.findViewById(R.id.tablature_view);
        noContentView = v.findViewById(R.id.no_tablature_text);
        tablatureText = v.findViewById(R.id.tablature_text);
        fullscreenButton = v.findViewById(R.id.fullscreen_button);
        return v;
    }

    @Override
    protected boolean isContentAvailable() {
        return tablature != null;
    }

    @Override
    protected void setContent() {
        tablatureText.setText(tablature);
    }

    @Override
    public void setSong(Song song) {
        tablature = song.getTablature();
    }
}
