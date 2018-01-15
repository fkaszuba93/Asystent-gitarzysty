package pd.asystentgitarzysty.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import pd.asystentgitarzysty.R;
import pd.asystentgitarzysty.adapter.SongsRecyclerViewAdapter;
import pd.asystentgitarzysty.content.Songs;
import pd.asystentgitarzysty.model.Song;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class SongsFragment extends Fragment {

    private Spinner spinner;
    private RecyclerView recyclerView;
    private OnListFragmentInteractionListener mListener;

    private Comparator<Song> comparator;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SongsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_songs_list, container, false);

        // Set the adapter
        Context context = view.getContext();
        recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new SongsRecyclerViewAdapter(Songs.getSongsList(), mListener));

        spinner = view.findViewById(R.id.spinner);
        setSpinner();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void refreshSongsList(){
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    public void sortSongsList(){
        List<Song> list = Songs.getSongsList();
        Collections.sort(list, comparator);
        refreshSongsList();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        void onSelectSong(Song s);
        void onDeleteSong(Song s);
    }

    private void setSpinner(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.sort_options,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new SpinnerSelectionListener());
        spinner.setSelection(0);
    }

    private class SpinnerSelectionListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
            List<Comparator<Song>> comparators = Arrays.asList(Song.ARTIST_COMPARATOR, Song.TITLE_COMPARATOR);
            comparator = comparators.get(position);
            sortSongsList();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }
}
