package com.example.vehicledrivergui.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.example.vehicledrivergui.R;
import com.example.vehicledrivergui.packet.OutputPacket;
import com.example.vehicledrivergui.packet.transportPacket.Transport;
import com.example.vehicledrivergui.packet.transportPacket.TransportWorker;

public class ControlsFragment extends Fragment {

    private SeekBar accelerationSeekbar;
    private SeekBar directionSeekbar;

    private OutputPacket buildPacket(){
        OutputPacket outputPacket = new OutputPacket();
        outputPacket.setAcceleration((accelerationSeekbar.getProgress()-50)*2);
        outputPacket.setDirection((directionSeekbar.getProgress()-50)*2);
        return outputPacket;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Transport transport = Transport.getInstance();

        View view = inflater.inflate(R.layout.fragment_controls, container, false);

        accelerationSeekbar = view.findViewById(R.id.acceleration);
        accelerationSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
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

        directionSeekbar = view.findViewById(R.id.direction);
        directionSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
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





        return view;


    }

    public void onDestroy() {
        super.onDestroy();
        System.out.println("Threads closed"); //DEBUG
        TransportWorker transportworker = new TransportWorker();
        transportworker.threadExit();
    }

}

