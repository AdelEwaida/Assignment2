package com.example.assignment2;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TimerActivity extends Activity {

    private int seconds = 0;
    private boolean running;
    String []hours = new String[24];
    String []minutes = new String[60];
    String []secondss = new String[60];
    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer_activity);

        if(savedInstanceState !=null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }

          fillspinners();

    }  @Override
    public void onSaveInstanceState(Bundle bundle){
        bundle.putInt("seconds", seconds);
        bundle.putBoolean("running", running);
    }


public void fillspinners(){
//        hours[0]= "0 hours";
//        minutes[0]= "0 min";
//        secondss[0]= "0 sec";
    for(int i =0;i<24;i++) {
        hours[i] = String.valueOf(i);
    }
    for(int i =0;i<60;i++){
        minutes[i] = String.valueOf(i);
    }   for(int i =0;i<60;i++){
        secondss[i] = String.valueOf(i);
    }
    spinner1 = findViewById(R.id.shours);
    spinner2 = findViewById(R.id.sminutes);
    spinner3 = findViewById(R.id.sseconds);

    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, hours);
    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner1.setAdapter(arrayAdapter);


   arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, minutes);
    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner2.setAdapter(arrayAdapter);
    arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, secondss);
    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner3.setAdapter(arrayAdapter);
}
        public void onClickStart(View view) {
            running = true;
            runTimer();

        }

        public void onClickStop(View view) {
            running = false;
        }

        public void onClickReset(View view) {

            running = true;
            runTimer();

        }
        private void runTimer(){
            String spin1 = spinner1.getSelectedItem().toString();
            String spin2 = spinner2.getSelectedItem().toString();
            String spin3 = spinner3.getSelectedItem().toString();
             final int hours1 = Integer.parseInt(spin1);
             final int min1=Integer.parseInt(spin2);
             final int sec1=Integer.parseInt(spin3);
            seconds=(hours1*3600)+(min1*60)+sec1;
            final TextView txtView = (TextView) findViewById(R.id.txtView);
            final Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {

                    int hours = seconds/3600;
                    int minutes = seconds % 3600 /60;
                    int snds = seconds % 60;
                    String time = String.format(Locale.getDefault(),
                            "%d:%02d:%02d", hours, minutes, snds);
                    txtView.setText(time);
                    if(running){
                    if(seconds==0)
                         running=false;
                    else
                        seconds--;
                    }
                    handler.postDelayed(this, 1000);
                }
            });
        }



    }