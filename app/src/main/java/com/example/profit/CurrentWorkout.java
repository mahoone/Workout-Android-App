package com.example.profit;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.ArrayList;

public class CurrentWorkout extends AppCompatActivity {

    // Declare Strings for Listview items.

    String[] TIMES = {"0:30", "0:30", "0:20"};
    String[] NAMES = {"Knee Crunches", "Sit ups", "Hanging leg raises"};
    String[] SETSREPS = {"3 Sets/10 Reps", "2 Sets/10 Reps", "1 Sets/10 Reps"};

    private VideoView videoView;
    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<String> videoList;
    private Chronometer chronometer;
    private Button play;
    private Button finish;
    private boolean running;
    private long pauseOffset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_workout);
        CustomAdapter customAdapter = new CustomAdapter();
        userUI();

        //Videos

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoView.isPlaying()) {
                    videoView.pause();
                    play.setBackgroundResource(R.drawable.ic_play_arrow_black_24dp);
                }else {
                    videoView.start();
                    play.setBackgroundResource(R.drawable.ic_pause_black_24dp);
                }
            }
        });

        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.abcrunch));
                        startChronometer(videoView);
                        break;
                    case 1:
                        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.upright));
                        break;
                    case 2:
                        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.kicks));
                        break;
                    default:
                        break;
                }
                videoView.requestFocus();;
                videoView.start();
            }
        });

        // Finish workout.

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseChronometer(videoView);
                videoView.pause();
                Intent intent = new Intent(CurrentWorkout.this, workout_complated.class);
                startActivity(intent);
            }
        });
    }

    //Chronometer Start/Stop/Pause

    public void startChronometer(View v) {
        if (!running){
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
        }
    }

    public void pauseChronometer(View v) {
        if (running){
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        }
    }

    public void resetChronometer(View v) {
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
    }


    //Declare UI's.

    private void userUI(){
        videoView = (VideoView) findViewById(R.id.videoViewActive);
        listView = (ListView)findViewById(R.id.listView);
        videoList = new ArrayList<>();
        chronometer = findViewById(R.id.chronometer);
        chronometer.setFormat("%s");
        play = (Button)findViewById(R.id.btnPlay);
        finish = (Button)findViewById(R.id.btnFinishWorkout);
    }

    //Custom layout adapter for listview. Allows custom listview.

    class CustomAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return TIMES.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.adapter_view_layout, null);
            }
            TextView textViewTime = (TextView)convertView.findViewById(R.id.viewTime);
            TextView textView_Name = (TextView)convertView.findViewById(R.id.textView_name);
            TextView textView_setsreps = (TextView)convertView.findViewById(R.id.textView_setsreps);

            textViewTime.setText(TIMES[position]);
            textView_Name.setText(NAMES[position]);
            textView_setsreps.setText(SETSREPS[position]);

            return convertView;
        }
    }
}
