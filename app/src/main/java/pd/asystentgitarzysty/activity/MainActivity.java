package pd.asystentgitarzysty.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import java.util.Arrays;
import java.util.List;

import pd.asystentgitarzysty.fragment.*;
import pd.asystentgitarzysty.R;
import pd.asystentgitarzysty.model.Song;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] STORAGE_PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private TabHost tabHost;
    private Spinner spinner;
    private ChordsFragment chordsFragment;
    private LyricsFragment lyricsFragment;
    private TablatureFragment tablatureFragment;

    private List<Song> songs = Arrays.asList(
            new Song("", "test"),
            new Song("qwerty", "test2")
    );
    private int currentSong = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        tabHost = findViewById(R.id.tabhost);
        tabHost.setup();
        addTab(R.string.tablature, R.id.tab1);
        addTab(R.string.chords, R.id.tab2);
        addTab(R.string.lyrics, R.id.tab3);
        setSpinner();
        verifyStoragePermissions();
        addFragments();
    }

    private void addTab(int labelId, int content){
        String indicator = getString(labelId);
        TabSpec tab = tabHost.newTabSpec(indicator);
        tab.setIndicator(indicator);
        tab.setContent(content);
        tabHost.addTab(tab);
    }

    private void addFragments(){
        tablatureFragment = new TablatureFragment();
        chordsFragment = new ChordsFragment();
        lyricsFragment = new LyricsFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.tab1, tablatureFragment)
                .add(R.id.tab2, chordsFragment)
                .add(R.id.tab3, lyricsFragment)
                .commit();
    }

    public Song getCurrentSong(){
        return songs.get(currentSong);
    }

    private void setSpinner(){
        ArrayAdapter<Song> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, songs);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new SongsSpinnerSelectionListener());
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

    private class SongsSpinnerSelectionListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
            currentSong = position;
            lyricsFragment.setSong(getCurrentSong());
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
}
