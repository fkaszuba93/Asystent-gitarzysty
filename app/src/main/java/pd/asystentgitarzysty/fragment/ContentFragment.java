package pd.asystentgitarzysty.fragment;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import pd.asystentgitarzysty.activity.MainActivity;
import pd.asystentgitarzysty.content.Songs;
import pd.asystentgitarzysty.model.Song;


public abstract class ContentFragment extends Fragment {
    
    protected TextView contentText, noContentText;
    protected View contentView;
    protected String content;
    protected ImageButton fullscreenButton;

    @Override
    public void onResume() {
        super.onResume();
        setSong(Songs.getCurrentSong());
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
