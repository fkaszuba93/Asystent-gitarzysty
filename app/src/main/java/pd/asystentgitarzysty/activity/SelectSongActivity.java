package pd.asystentgitarzysty.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import pd.asystentgitarzysty.R;
import pd.asystentgitarzysty.content.Songs;
import pd.asystentgitarzysty.fragment.AddSongDialogFragment;
import pd.asystentgitarzysty.fragment.SongsFragment;
import pd.asystentgitarzysty.model.Song;


public class SelectSongActivity extends AppCompatActivity implements SongsFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_song);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.songs_fragment_container, new SongsFragment())
                .commit();
        setAddButton();
    }

    @Override
    public void onListFragmentInteraction(Song song) {
        int index = Songs.getSongsList().indexOf(song);
        Songs.setCurrentSong(index);
        setResult(RESULT_OK);
        finish();
    }

    private void setAddButton(){
        FloatingActionButton addSongButton = findViewById(R.id.add_song_button);
        addSongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddSongDialogFragment().show(getSupportFragmentManager(), "addSong");
            }
        });
    }
}
