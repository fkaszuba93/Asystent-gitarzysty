package pd.asystentgitarzysty.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import pd.asystentgitarzysty.R;
import pd.asystentgitarzysty.activity.SelectSongActivity;
import pd.asystentgitarzysty.content.Songs;
import pd.asystentgitarzysty.model.Song;

public class DeleteSongDialogFragment extends DialogFragment {

    public static final String TITLE = "pd.asystentgitarzysty.fragment.title";
    public static final String INDEX = "pd.asystentgitarzysty.fragment.index";

    private TextView titleText;
    private CheckBox checkBox;
    private int index;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        SelectSongActivity activity = (SelectSongActivity) getActivity();
        LayoutInflater inflater = activity.getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_delete_song_dialog, null);
        findView(v);
        getArgs();

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(v)
                .setPositiveButton(R.string.delete, new DeleteButtonOnClickListener())
                .setNegativeButton(R.string.cancel, null);
        return builder.create();
    }

    private void getArgs() {
        Bundle args = getArguments();
        titleText.setText(args.getString(TITLE));
        index = args.getInt(INDEX);
    }

    private void findView(View v){
        titleText = v.findViewById(R.id.title_text);
        checkBox = v.findViewById(R.id.check_box);
    }


    private class DeleteButtonOnClickListener implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            Song song = Songs.getSongsList().get(index);
            Songs.delete(song);
            if (checkBox.isChecked())
                song.deleteContent();
            SongsFragment songsFragment = (SongsFragment) getActivity().getSupportFragmentManager().findFragmentByTag("songs");
            songsFragment.refreshSongsList();
        }
    }
}
