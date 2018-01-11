package pd.asystentgitarzysty.model;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;

import static android.media.AudioAttributes.*;

import java.util.Timer;
import java.util.TimerTask;

import pd.asystentgitarzysty.R;

public class Metronome {

    private int beats, tempo;
    private final int click1, click2;
    private boolean running = false;
    private SoundPool soundPool;
    private Timer timer;

    public Metronome(Context context){
        AudioAttributes attrs = new AudioAttributes.Builder()
                .setContentType(CONTENT_TYPE_SONIFICATION)
                .setUsage(USAGE_UNKNOWN)
                .build();
        soundPool = new SoundPool.Builder()
                .setMaxStreams(1)
                .setAudioAttributes(attrs)
                .build();
        click1 = soundPool.load(context, R.raw.click1, 1);
        click2 = soundPool.load(context, R.raw.click2, 1);
    }

    public void setBeats(int beats) {
        this.beats = beats;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
        if (isRunning()) {
            stop();
            start();
        }
    }

    public void start(){
        if (beats < 1 || tempo < 1)
            throw new IllegalStateException("beats and tempo must be greater than 0");
        if (isRunning())
            throw new IllegalStateException("already started");
        timer = new Timer();
        long period = (long) (1000 / ((float) tempo / 60));
        timer.schedule(new PlayClickTask(), 0, period);
        running = true;
    }

    public void stop(){
        if (timer != null)
            timer.cancel();
        running = false;
    }

    public boolean isRunning(){
        return running;
    }

    private void play(int id){
        soundPool.play(id, 1, 1, 1, 0, 1);
    }

    private class PlayClickTask extends TimerTask {

        private int beat = 0;

        @Override
        public void run() {
            if (beat == 0)
                play(click1);
            else
                play(click2);
            beat = (beat + 1) % beats;
        }
    }
}
