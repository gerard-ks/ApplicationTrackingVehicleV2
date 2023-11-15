package ci.esatic.applicationtrackingvehiclev2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {

    private TextView textView1;
    private TextView textView2;
    private static int splash_timeout = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        this.textView1 = findViewById(R.id.textBienvenue);
        this.textView2 = findViewById(R.id.textTitre);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }, splash_timeout);

        Animation animation = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.animation2);
        textView1.startAnimation(animation);

        Animation animation1 = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.animation1);
        textView2.startAnimation(animation1);

    }
}