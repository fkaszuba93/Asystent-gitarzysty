package pd.asystentgitarzysty.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import pd.asystentgitarzysty.R;
import pd.asystentgitarzysty.model.Metronome;

/**
 * A simple {@link Fragment} subclass.
 */
public class MetronomeFragment extends Fragment {

    private static final String BEATS = "pd.asystentgitarzysty.fragment.beats";
    private static final String TEMPO = "pd.asystentgitarzysty.fragment.tempo";
    private static final String BUTTON_TEXT = "pd.asystentgitarzysty.fragment.buttonText";

    private NumberPicker beatsPicker, tempoPicker;
    private Button startStopButton;
    private Metronome metronome;

    public MetronomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        metronome = Metronome.getInstance(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_metronome, container, false);
        beatsPicker = v.findViewById(R.id.beats_picker);
        tempoPicker = v.findViewById(R.id.tempo_picker);
        startStopButton = v.findViewById(R.id.start_stop_button);
        setStartStopButton();
        setBeatsPicker();
        setTempoPicker();
        if (savedInstanceState != null){
            beatsPicker.setValue(savedInstanceState.getInt(BEATS));
            tempoPicker.setValue(savedInstanceState.getInt(TEMPO));
            startStopButton.setText(savedInstanceState.getString(BUTTON_TEXT));
        }
        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putInt(BEATS, beatsPicker.getValue());
        state.putInt(TEMPO, tempoPicker.getValue());
        state.putString(BUTTON_TEXT, startStopButton.getText().toString());
    }

    private void setBeatsPicker(){
        beatsPicker.setMinValue(1);
        beatsPicker.setMaxValue(64);
        beatsPicker.setValue(4);
        beatsPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
                metronome.setBeats(newVal);
            }
        });
    }

    private void setTempoPicker(){
        tempoPicker.setMinValue(40);
        tempoPicker.setMaxValue(300);
        tempoPicker.setValue(120);
        tempoPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
                metronome.setTempo(newVal);
            }
        });
    }

    private void setStartStopButton() {
        startStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!metronome.isRunning()){
                    metronome.setBeats(beatsPicker.getValue());
                    metronome.setTempo(tempoPicker.getValue());
                    metronome.start();
                    startStopButton.setText(R.string.stop);
                } else {
                    metronome.stop();
                    startStopButton.setText(R.string.start);
                }
            }
        });
    }
}
