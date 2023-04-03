package com.example.vehicledrivergui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.SeekBar;

import com.example.vehicledrivergui.packet.OutputPacket;

public class MainActivity extends AppCompatActivity {

    private SeekBar accelerationSeekbar;
    private SeekBar directionSeekbar;

    private OutputPacket buildPacket(){
        OutputPacket outputPacket = new OutputPacket();
        outputPacket.setAcceleration((accelerationSeekbar.getProgress()-50)*2);
        outputPacket.setDirection((directionSeekbar.getProgress()-50)*2);
        return outputPacket;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Transport transport = new Transport();

        accelerationSeekbar = findViewById(R.id.acceleration);
        accelerationSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                   // System.out.println(i-50);
                    transport.send(buildPacket());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int max = seekBar.getMax();
                int mid = max / 2;
                seekBar.setProgress(mid);
                transport.send(buildPacket());
            }

            });

        directionSeekbar = findViewById(R.id.direction);
        directionSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
              //  System.out.println(i-50);
                transport.send(buildPacket());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int max = seekBar.getMax();
                int mid = max / 2;
                seekBar.setProgress(mid);
                transport.send(buildPacket());
            }

        });




        }


}