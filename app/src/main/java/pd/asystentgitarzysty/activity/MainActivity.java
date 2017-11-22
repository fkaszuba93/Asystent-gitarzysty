package pd.asystentgitarzysty.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import pd.asystentgitarzysty.fragment.*;
import pd.asystentgitarzysty.R;

public class MainActivity extends AppCompatActivity {

    private TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabHost = findViewById(R.id.tabhost);
        tabHost.setup();
        addTab(R.string.tablature, R.id.tab1);
        addTab(R.string.chords, R.id.tab2);
        addTab(R.string.lyrics, R.id.tab3);
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
        getSupportFragmentManager().beginTransaction()
                .add(R.id.tab1, new TablatureFragment())
                .add(R.id.tab2, new ChordsFragment())
                .add(R.id.tab3, new LyricsFragment())
                .commit();
    }
}
