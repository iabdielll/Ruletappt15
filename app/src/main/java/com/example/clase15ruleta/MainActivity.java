package com.example.clase15ruleta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    ConstraintLayout cl;
    SensorManager sm;
    Sensor s;
    TextView texto;
    ImageView ruleta;
    Random ran;
    int angulo;
    boolean restart = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ruleta = findViewById(R.id.ruleta);
        cl = findViewById(R.id.caja);
        texto = findViewById(R.id.tv);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        s = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sm.registerListener((SensorEventListener) this,s,SensorManager.SENSOR_DELAY_NORMAL);
        ran = new Random();
    }
    @Override
    public void onSensorChanged(SensorEvent e){
        String txt1 = String.valueOf(e.values[0]);
        texto.setText(txt1);
        float valor = Float.parseFloat(txt1);
        if (valor == 0){
            angulo = ran.nextInt(360)+360;
            RotateAnimation rotate = new RotateAnimation(0,angulo,RotateAnimation.RELATIVE_TO_SELF,
                    0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
            rotate.setFillAfter(true);
            rotate.setDuration(3600);
            rotate.setInterpolator(new AccelerateInterpolator());
            ruleta.startAnimation(rotate);
            restart = false;
        }else{
            texto.setText("Pasa tu mano para jugar");
        }
    }
    @Override
    public void onAccuracyChanged(Sensor s, int i){

    }
}