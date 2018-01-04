package pd.asystentgitarzysty.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import pd.asystentgitarzysty.content.Songs;
import pd.asystentgitarzysty.fragment.*;
import pd.asystentgitarzysty.R;


public class MainActivity extends AppCompatActivity {

    private static final String TAB = "pd.asystentgitarzysty.activity.tab";
    private static final int REQUEST_CODE_SELECT_SONG = 1;
    private static final int REQUEST_EXTERNAL_STORAGE = 2;
    private static String[] STORAGE_PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private TabHost tabHost;
    private Button songsButton;
    private TextView currentSongText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();

        tabHost.setup();
        addTab(R.string.tablature, R.id.tab1);
        addTab(R.string.chords, R.id.tab2);
        addTab(R.string.lyrics, R.id.tab3);
        setSongsButton();
        verifyStoragePermissions();

        if (savedInstanceState != null){
            tabHost.setCurrentTab(savedInstanceState.getInt(TAB));
        } else {
            addFragments();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putInt(TAB, tabHost.getCurrentTab());
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateTitle();
    }

    private void findView(){
        tabHost = findViewById(R.id.tabhost);
        songsButton = findViewById(R.id.songs_button);
        currentSongText = findViewById(R.id.current_song_text);
    }

    private void addTab(int labelId, int content){
        String indicator = getString(labelId);
        TabSpec tab = tabHost.newTabSpec(indicator);
        tab.setIndicator(indicator);
        tab.setContent(content);
        tabHost.addTab(tab);
    }

    private void addFragments(){
        getSupportFragmentManager().beginTransaction()
                .add(R.id.tab1, new TablatureFragment())
                .add(R.id.tab2, new ChordsFragment())
                .add(R.id.tab3, new LyricsFragment())
                .commit();
    }

    private void setSongsButton(){
        songsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(
                        new Intent(MainActivity.this, SelectSongActivity.class),
                        REQUEST_CODE_SELECT_SONG
                );
            }
        });
    }

    private void updateTitle(){
        String title = Songs.getCurrentSong().toString();
        currentSongText.setText(title);
    }

    public static boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }

    public void verifyStoragePermissions() {
        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, STORAGE_PERMISSIONS, REQUEST_EXTERNAL_STORAGE);
        }
    }
}
