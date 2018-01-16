package pd.asystentgitarzysty.fragment;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageButton;

import pd.asystentgitarzysty.activity.MainActivity;
import pd.asystentgitarzysty.content.Songs;
import pd.asystentgitarzysty.model.Song;


public abstract class ContentFragment extends Fragment {

    protected View contentView, noContentView;
    protected ImageButton fullscreenButton;

    @Override
    public void onResume() {
        super.onResume();
        setSong(Songs.getCurrentSong());
        displayContent();
        setFullscreenButton();
    }

    private void setFullscreenButton(){
        if (fullscreenButton != null){
            fullscreenButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity activity = (MainActivity) getActivity();
                    activity.setFullscreen(!activity.isFullscreen());
                }
            });
        }
    }

    protected void displayContent(){
        if (isContentAvailable()) {
            setContent();
            contentView.setVisibility(View.VISIBLE);
            noContentView.setVisibility(View.GONE);
        } else {
            contentView.setVisibility(View.GONE);
            noContentView.setVisibility(View.VISIBLE);
        }
    }

    protected abstract boolean isContentAvailable();

    protected abstract void setContent();

    public abstract void setSong(Song s);

}
