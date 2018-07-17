package com.androidmorefast.pc.appvibrador01;

import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnOpc1;
    private Button btnOpc2;
    private Button btnCancel;
    private boolean isVibrating = false;
    private Vibrator vibrador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOpc1 = (Button) findViewById(R.id.btn_brivate_once);
        btnOpc2 = (Button) findViewById(R.id.btn_brivate_repeat);
        btnCancel = (Button) findViewById(R.id.btn_brivate_cancel);
        vibrador = ((Vibrator) getSystemService(VIBRATOR_SERVICE));
        vibrador.cancel();
    }

    public void performVibrateOnce(View view) {
        if (!vibrador.hasVibrator()) {
            Toast.makeText(MainActivity.this,
                    "No soporta Vibrador", Toast.LENGTH_LONG).show();
            return;
        }
        if (isVibrating)
            vibrador.cancel();
        vibrador.vibrate(1000L);
        isVibrating = true;
        btnCancel.setEnabled(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isVibrating = false;
                btnCancel.setEnabled(false);
            }
        }, 1000);
    }

    public void performVibrateRepeat(View view) {
        if (!vibrador.hasVibrator()) {
            Toast.makeText(MainActivity.this,
                    "No esta activado", Toast.LENGTH_LONG).show();
            return;
        }
        if (isVibrating)
            vibrador.cancel();

        long delay = 250;
        long keeVip = 250;
        long on_off = 250;
        int repeat = 1;

        vibrador.vibrate(new long[]{delay, keeVip, on_off, on_off, on_off}, repeat);
        isVibrating = true;
        btnCancel.setEnabled(true);
    }

    public void performVibrateCancel(View view) {
        if (!vibrador.hasVibrator()) {
            Toast.makeText(MainActivity.this,
                    "No hay Vibrador", Toast.LENGTH_LONG).show();
            return;
        }
        if (isVibrating) {
            vibrador.cancel();
            btnCancel.setEnabled(false);
            isVibrating = false;
        }

    }

}
