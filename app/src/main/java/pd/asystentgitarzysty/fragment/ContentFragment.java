package pd.asystentgitarzysty.fragment;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import pd.asystentgitarzysty.content.Songs;
import pd.asystentgitarzysty.model.Song;


public abstract class ContentFragment extends Fragment {
    
    protected TextView contentText, noContentText;
    protected View contentView;
    protected String content;

    @Override
    public void onResume() {
        super.onResume();
        setSong(Songs.getCurrentSong());
    }

    protected void displayContent(){
        if (content != null) {
            contentText.setText(content);
            contentView.setVisibility(View.VISIBLE);
            noContentText.setVisibility(View.GONE);
        } else {
            contentView.setVisibility(View.GONE);
            noContentText.setVisibility(View.VISIBLE);
        }
    }

    public abstract void setSong(Song s);

}
