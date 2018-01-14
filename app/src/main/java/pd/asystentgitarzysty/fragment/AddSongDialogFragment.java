package pd.asystentgitarzysty.fragment;

import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import pd.asystentgitarzysty.R;
import pd.asystentgitarzysty.activity.SelectSongActivity;
import pd.asystentgitarzysty.content.Songs;
import pd.asystentgitarzysty.model.Song;

public class AddSongDialogFragment extends DialogFragment {

    private EditText artistText, titleText;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        SelectSongActivity activity = (SelectSongActivity) getActivity();
        LayoutInflater inflater = activity.getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_add_song_dialog, null);
        findView(v);

        Builder builder = new Builder(activity);
        builder.setView(v)
            .setPositiveButton(R.string.add, new AddButtonOnClickListener())
            .setNegativeButton(R.string.cancel, null);
        return builder.create();
    }

    private void findView(View v) {
        artistText = v.findViewById(R.id.artist_text);
        titleText = v.findViewById(R.id.title_text);
    }


    private class AddButtonOnClickListener implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            String artist = artistText.getText().toString();
            String title = titleText.getText().toString();
            Songs.add(new Song(artist, title));
        }
    }
}
