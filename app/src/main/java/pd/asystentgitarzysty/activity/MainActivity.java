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
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import pd.asystentgitarzysty.content.Songs;
import pd.asystentgitarzysty.fragment.*;
import pd.asystentgitarzysty.R;
import pd.asystentgitarzysty.model.Song;


public class MainActivity extends AppCompatActivity {

    private static final String TAB = "pd.asystentgitarzysty.activity.tab";
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] STORAGE_PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private TabHost tabHost;
    private Spinner spinner;
    private Button addButton;
    private ContentFragment chordsFragment = new ChordsFragment();
    private ContentFragment lyricsFragment = new LyricsFragment();
    private ContentFragment tablatureFragment = new TablatureFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();

        tabHost.setup();
        addTab(R.string.tablature, R.id.tab1);
        addTab(R.string.chords, R.id.tab2);
        addTab(R.string.lyrics, R.id.tab3);
        setSpinner();
        setAddButton();
        verifyStoragePermissions();
        addFragments();

        if (savedInstanceState != null){
            tabHost.setCurrentTab(savedInstanceState.getInt(TAB));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putInt(TAB, tabHost.getCurrentTab());
    }

    private void findView(){
        spinner = findViewById(R.id.spinner);
        tabHost = findViewById(R.id.tabhost);
        addButton = findViewById(R.id.add_button);
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
                .add(R.id.tab1, tablatureFragment)
                .add(R.id.tab2, chordsFragment)
                .add(R.id.tab3, lyricsFragment)
                .commit();
    }

    private void setSpinner(){
        ArrayAdapter<Song> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Songs.getSongsList());
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new SongsSpinnerSelectionListener());
    }

    private void setAddButton(){
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddSongDialogFragment().show(getSupportFragmentManager(), "addSong");
            }
        });
    }

    public void addSong(Song s){
        Songs.add(s);
        setSpinner();
    }

    private void updateFragments(){
        Song song = Songs.getCurrentSong();
        lyricsFragment.setSong(song);
        chordsFragment.setSong(song);
        tablatureFragment.setSong(song);
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
            Songs.setCurrentSong(position);
            updateFragments();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }
}
