package ir.oxima.progressbuttonapp;

import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import ir.oxima.progressbutton.ButtonState;
import ir.oxima.progressbutton.ProgressButton;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ProgressButton btn_progress;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setupToolbar();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setTitle(getText(R.string.app_name));
        toolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.colorAccent));
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        btn_progress = findViewById(R.id.btn_progress);
        btn_progress.setState(ButtonState.Default,"Download");
        btn_progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn_progress.getState() == ButtonState.Default){
                    startDownload();
                }

            }
        });
    }

    private void startDownload() {
        btn_progress.setState(ButtonState.Progress);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0 ; i<10000 ; i++){
                    String s = "";
                    s = s + i;
                    final int finalI = i;
                    handler.post(new Runnable() {
                       @Override
                       public void run() {
                           btn_progress.setProgress(finalI /100);
                       }
                   });
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        btn_progress.setState(ButtonState.Complete,"Install");
                    }
                });
            }
        }).start();
    }
}
