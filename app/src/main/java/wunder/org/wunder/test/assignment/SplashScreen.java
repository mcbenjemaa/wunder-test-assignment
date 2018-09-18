package wunder.org.wunder.test.assignment;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import butterknife.ButterKnife;

public class SplashScreen extends AppCompatActivity {


    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);



        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                    start();
            }
        },3000);

    }



    void start(){
        startActivity(new Intent(this,MainActivity.class));

        finish();
    }

}
