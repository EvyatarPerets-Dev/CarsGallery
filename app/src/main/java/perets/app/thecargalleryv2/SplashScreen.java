package perets.app.thecargalleryv2;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bitvale.lightprogress.LightProgress;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Timer;
import java.util.TimerTask;


/**
 * SplashScreen: 1) shows background photo + logo
 *               2) onClick sending to main activity.
 *               3) Doing Animation on the TextView.
 */
public class SplashScreen extends AppCompatActivity {
    ImageView logoIV;
    com.bitvale.lightprogress.LightProgress light;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        //Onclick passing to the main activity.
        findViewById(R.id.splashScreenLayout).setOnClickListener(v -> {
            light.off();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        light = findViewById(R.id.light);
        logoIV = findViewById(R.id.logoIV);

        light.setVisibility(View.INVISIBLE);


        animationOnLogo();

    }

    @Override
    protected void onResume() {
        super.onResume();



    }

    private void animationOnLogo() {
        YoYo.with(Techniques.Tada).onEnd(new YoYo.AnimatorCallback() {
            @Override
            public void call(Animator animator) {
                light.setVisibility(View.VISIBLE);
                light.on();
            }
        })
                .duration(1000)
                .repeat(2)
                .playOn(logoIV);

        YoYo.with(Techniques.ZoomInLeft)
                .duration(1000)
                .repeat(2)
                .playOn(logoIV);
    }
}