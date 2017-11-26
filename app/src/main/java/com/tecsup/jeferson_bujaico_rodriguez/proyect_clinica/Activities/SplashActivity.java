package com.tecsup.jeferson_bujaico_rodriguez.proyect_clinica.Activities;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tecsup.jeferson_bujaico_rodriguez.proyect_clinica.R;

public class SplashActivity extends AppCompatActivity {

    private ImageView iv;
    private TextView tv;
    private ProgressBar pb;
    public static final int CelesteOscuro = 0xec00e6ff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        pb = (ProgressBar)findViewById(R.id.pb_splash);
        iv = (ImageView)findViewById(R.id.iv_splash);
        tv = (TextView) findViewById(R.id.tv_splash);

        // se le llama a progressBar para cambiar el color de este
        pb.getIndeterminateDrawable()
                .setColorFilter(CelesteOscuro, PorterDuff.Mode.SRC_IN);

        //Se le asigna una animacion al splash tanto para el TextView y ImageView
        Animation myAnim = AnimationUtils.loadAnimation(this,R.anim.my_animation_splash);

        //-------- Se le asigna a s TextView y ImageView una animacion
        iv.startAnimation(myAnim);
        tv.startAnimation(myAnim);

        //-------- Se dclara que este splash se vera solo unos 3 seg. luego le enviara a la clase Introduccion
        Thread time = new Thread(){
            public void run(){
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }//final del try {}catch{}
                finally{
                    Intent intent = new Intent(SplashActivity.this, IntroduccionActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };//final de Thread{}
        time.start();

        //--------- Segun carag el progress var es de igual manera en el splash dentro de 3 seg.
        Handler hd = new Handler();
        hd.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Intent intent = new Intent(SplashActivity.this, IntroduccionActivity.class);
            }
        }, 3000);
    }
}
