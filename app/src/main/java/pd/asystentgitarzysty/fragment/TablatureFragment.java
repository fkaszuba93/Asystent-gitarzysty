package pd.asystentgitarzysty.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pd.asystentgitarzysty.R;
import pd.asystentgitarzysty.model.Song;


public class TablatureFragment extends ContentFragment {


    public TablatureFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tablature, container, false);
        contentView = v.findViewById(R.id.tablature_view);
        contentText = v.findViewById(R.id.tablature_text);
        noContentText = v.findViewById(R.id.no_tablature_text);
        isViewCreated = true;
        displayContent();
        return v;
    }

    @Override
    public void setSong(Song song) {
        content = song.getTablature();
        if (isViewCreated)
            displayContent();
    }
}
