package kup.moemoetun.shwegrammaroffline;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.android.gms.ads.MobileAds;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import kup.moemoetun.shwegrammaroffline.utility.GoogleMobileAdsConsentManager;
/** Splash Activity that inflates splash activity xml. */
public class SplashActivity extends AppCompatActivity {
    private static final String LOG_TAG = "SplashActivity";
    private final AtomicBoolean isMobileAdsInitializeCalled = new AtomicBoolean(false);
    private GoogleMobileAdsConsentManager googleMobileAdsConsentManager;

    private ImageView splashImage;
    private ProgressBar progressBar;
    private TextView loadingText;
    private AnimatorSet pulseAnimatorSet;

    /**
     * Number of milliseconds to count down before showing the app open ad. This simulates the time
     * needed to load the app.
     */
    private static final long COUNTER_TIME_MILLISECONDS = 6000;

    private long secondsRemaining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashImage = findViewById(R.id.splash_activity_image);
        progressBar = findViewById(R.id.loading_progress_bar);
        loadingText = findViewById(R.id.loading_text);
        View rootLayout = findViewById(R.id.splash_root);

        // Start animations
        startLogoAnimations();
        setupInteractiveAnimation(rootLayout);

        // Create a timer so the SplashActivity will be displayed for a fixed amount of time.
        createTimer(COUNTER_TIME_MILLISECONDS);

        googleMobileAdsConsentManager =
                GoogleMobileAdsConsentManager.getInstance(getApplicationContext());
        googleMobileAdsConsentManager.gatherConsent(
                this,
                consentError -> {
                    if (consentError != null) {
                        // Consent not obtained in current session.
                        Log.w(
                                LOG_TAG,
                                String.format(
                                        "%s: %s", consentError.getErrorCode(), consentError.getMessage()));
                    }

                    if (googleMobileAdsConsentManager.canRequestAds()) {
                        initializeMobileAdsSdk();
                    }

                    if (secondsRemaining <= 0) {
                        startMainActivity();
                    }
                });

        // This sample attempts to load ads using consent obtained in the previous session.
        if (googleMobileAdsConsentManager.canRequestAds()) {
            initializeMobileAdsSdk();
        }
    }

    private void startLogoAnimations() {
        // Pulse effect
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(splashImage, View.SCALE_X, 1f, 1.15f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(splashImage, View.SCALE_Y, 1f, 1.15f, 1f);
        scaleX.setRepeatCount(ValueAnimator.INFINITE);
        scaleY.setRepeatCount(ValueAnimator.INFINITE);
        scaleX.setDuration(2000);
        scaleY.setDuration(2000);

        // Rotation effect
        ObjectAnimator rotate = ObjectAnimator.ofFloat(splashImage, View.ROTATION, 0f, 10f, 0f, -10f, 0f);
        rotate.setRepeatCount(ValueAnimator.INFINITE);
        rotate.setDuration(3000);

        pulseAnimatorSet = new AnimatorSet();
        pulseAnimatorSet.playTogether(scaleX, scaleY, rotate);
        pulseAnimatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        pulseAnimatorSet.start();
    }

    private void setupInteractiveAnimation(View root) {
        root.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                // Interactive reaction: Scale the logo quickly when screen is touched
                ScaleAnimation interactiveScale = new ScaleAnimation(
                        1f, 1.3f, 
                        1f, 1.3f, 
                        Animation.RELATIVE_TO_SELF, 0.5f, 
                        Animation.RELATIVE_TO_SELF, 0.5f);
                interactiveScale.setDuration(300);
                interactiveScale.setRepeatCount(1);
                interactiveScale.setRepeatMode(Animation.REVERSE);
                splashImage.startAnimation(interactiveScale);
                return true;
            }
            return false;
        });
    }

    /**
     * Create the countdown timer, which counts down to zero and show the app open ad.
     *
     * @param time the number of milliseconds that the timer counts down from
     */
    private void createTimer(long time) {
        CountDownTimer countDownTimer =
                new CountDownTimer(time, 50) { // Update more frequently for smooth progress
                    @Override
                    public void onTick(long millisUntilFinished) {
                        secondsRemaining = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) + 1;
                        
                        // Update progress bar
                        int progress = (int) (((time - millisUntilFinished) * 100) / time);
                        progressBar.setProgress(progress);
                        
                        if (loadingText != null) {
                            loadingText.setText("Loading... " + progress + "%");
                        }
                    }

                    @Override
                    public void onFinish() {
                        secondsRemaining = 0;
                        progressBar.setProgress(100);
                        if (loadingText != null) {
                            loadingText.setText("Loaded!");
                        }

                        Application application = getApplication();
                        ((MyApplication) application)
                                .showAdIfAvailable(
                                        SplashActivity.this,
                                        new MyApplication.OnShowAdCompleteListener() {
                                            @Override
                                            public void onShowAdComplete() {
                                                // Check if the consent form is currently on screen before moving to the
                                                // main
                                                // activity.
                                                if (googleMobileAdsConsentManager.canRequestAds()) {
                                                    startMainActivity();
                                                }
                                            }
                                        });
                    }
                };
        countDownTimer.start();
    }

    private void initializeMobileAdsSdk() {
        if (isMobileAdsInitializeCalled.getAndSet(true)) {
            return;
        }

        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(this);

        // Load an ad.
        Application application = getApplication();
        ((MyApplication) application).loadAd(this);
    }

    /** Start the MainActivity. */
    public void startMainActivity() {
        if (pulseAnimatorSet != null) {
            pulseAnimatorSet.cancel();
        }
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }
}
