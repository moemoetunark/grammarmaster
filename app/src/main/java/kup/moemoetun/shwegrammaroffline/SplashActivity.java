package kup.moemoetun.shwegrammaroffline;
import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/** Splash Activity that inflates splash activity xml. */
public class SplashActivity extends AppCompatActivity {
  private static final long COUNTER_TIME_MILLISECONDS = 5000;

  private long secondsRemaining;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
    // Create a timer so the SplashActivity will be displayed for a fixed amount of time.
    createTimer(COUNTER_TIME_MILLISECONDS);

  }

  /**
   * Create the countdown timer, which counts down to zero and show the app open ad.
   *
   * @param time the number of milliseconds that the timer counts down from
   */
  private void createTimer(long time) {
    final TextView counterTextView = findViewById(R.id.timer);

    CountDownTimer countDownTimer =
        new CountDownTimer(time, 1000) {
          @SuppressLint("SetTextI18n")
          @Override
          public void onTick(long millisUntilFinished) {
            secondsRemaining = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) + 1;
            counterTextView.setText("App is done loading in: " + secondsRemaining);
          }

          @SuppressLint("SetTextI18n")
          @Override
          public void onFinish() {
            secondsRemaining = 0;
            counterTextView.setText("Done.");
            startMainActivity();
          }
        };
    countDownTimer.start();
  }


  /** Start the MainActivity. */
  public void startMainActivity() {
    Intent intent = new Intent(this, MainActivity.class);
    this.startActivity(intent);
  }
}
