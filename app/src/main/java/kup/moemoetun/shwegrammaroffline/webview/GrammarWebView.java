package kup.moemoetun.shwegrammaroffline.webview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import kup.moemoetun.shwegrammaroffline.R;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowMetrics;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.Arrays;
import java.util.List;

public class GrammarWebView extends AppCompatActivity {
    public WebView webView;
    public InterstitialAd mInterstitialAd;

    private static final String AD_UNIT_ID = "ca-app-pub-4137439985376631/4189931024";
    private AdView adView;
    private FrameLayout adContainerView;
    private boolean initialLayoutComplete = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar_web_view);
        MobileAds.initialize(this, initializationStatus -> {
        });

        adContainerView = findViewById(R.id.ad_view_container);
        adView = new AdView(this);
        adContainerView.addView(adView);
        // Since we're loading the banner based on the adContainerView size, we need
        // to wait until this view is laid out before we can get the width.
        adContainerView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        if (!initialLayoutComplete) {
                            initialLayoutComplete = true;
                            loadBanner();
                        }
                    }
                });

        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(this, getString(R.string.offline_onback),
                adRequest, new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                    }
                });
        //Toolbar toolbar = findViewById(R.id.toolbar);/
        webView = (WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(),"Hello World", Toast.LENGTH_SHORT).show();
        });
        int intExtra = getIntent().getIntExtra("key",0);

        if (intExtra == 0) {
            webView.loadUrl("file:///android_asset/player/unit_1_am_is_are.html");
        } else if (intExtra == 1) {
            webView.loadUrl("file:///android_asset/player/unit_2_am_is_are_question.html");
        }else if (intExtra == 2) {
            webView.loadUrl("file:///android_asset/player/unit_3_present_continuous.html");
        }else if (intExtra == 3) {
            webView.loadUrl("file:///android_asset/player/unit_4_present_continuous_question.html");
        }else if (intExtra == 4) {
            webView.loadUrl("file:///android_asset/player/unit_5_present_simple.html");
        }else if (intExtra == 5) {
            webView.loadUrl("file:///android_asset/player/unit_6_present_simple_negative.html");
        }else if (intExtra == 6) {
            webView.loadUrl("file:///android_asset/player/unit_7_present_simple_question.html");
        }else if (intExtra == 7) {
            webView.loadUrl("file:///android_asset/player/unit_8_present_continuous_and_present_simple.html");
        }else if (intExtra == 8) {
            webView.loadUrl("file:///android_asset/player/unit_9_i_have_got.html");
        }else if (intExtra == 9) {
            webView.loadUrl("file:///android_asset/player/unit_10_was_were.html");
        }else if (intExtra == 10) {
            webView.loadUrl("file:///android_asset/player/unit_11_past_Simple.html");
        }else if (intExtra == 11) {
            webView.loadUrl("file:///android_asset/player/unit_12_past_simple.html");
        }

        else if (intExtra == 12) {
            webView.loadUrl("file:///android_asset/player/unit_13_I_was_doing_past_continuous.html");
        }else if (intExtra == 13) {
            webView.loadUrl("file:///android_asset/player/unit_14_past_simple_past_continuous.html");
        }else if (intExtra == 14) {
            webView.loadUrl("file:///android_asset/player/unit_15_present_perfect.html");
        }else if (intExtra == 15) {
            webView.loadUrl("file:///android_asset/player/unit_16_present_perfect_2.html");
        }else if (intExtra == 16) {
            webView.loadUrl("file:///android_asset/player/unit_17_present_perfect_3.html");
        }else if (intExtra == 17) {
            webView.loadUrl("file:///android_asset/player/unit_19_for_since_ago.html");
        }else if (intExtra == 18) {
            webView.loadUrl("file:///android_asset/player/unit_20_present_perfect_n_past_simple.html");
        }else if (intExtra == 19) {
            webView.loadUrl("file:///android_asset/player/unit_21_passive_voice.html");
        }else if (intExtra == 20) {
            webView.loadUrl("file:///android_asset/player/unit_22_passive_voice_present_perfect.html");
        }else if (intExtra == 21) {
            webView.loadUrl("file:///android_asset/player/unit_23_present_perfect_n_past.html");
        }else if (intExtra == 22) {
            webView.loadUrl("file:///android_asset/player/unit_24_used_to.html");
        }else if (intExtra == 23) {
            webView.loadUrl("file:///android_asset/player/unit_26_what_are_you_doing_tomorrow.html");
        }else if (intExtra == 24) {
            webView.loadUrl("file:///android_asset/player/unit_27_going_to.html");
        }else if (intExtra == 25) {
            webView.loadUrl("file:///android_asset/player/unit_28_will_shall.html");
        }else if (intExtra == 26) {
            webView.loadUrl("file:///android_asset/player/unit_29_will_shall_2.html");
        }else if (intExtra == 27) {
            webView.loadUrl("file:///android_asset/player/unit_30_might.html");
        }else if (intExtra == 28) {
            webView.loadUrl("file:///android_asset/player/unit_31_can_could.html");
        }else if (intExtra == 29) {
            webView.loadUrl("file:///android_asset/player/unit_32_must_musn't_needn't.html");
        }else if (intExtra == 30) {
            webView.loadUrl("file:///android_asset/player/unit_33_should.html");
        }else if (intExtra == 31) {
            webView.loadUrl("file:///android_asset/player/unit_34_have_to.html");
        }else if (intExtra == 32) {
            webView.loadUrl("file:///android_asset/player/unit_35_would_you_like.html");
        }else if (intExtra == 33) {
            webView.loadUrl("file:///android_asset/player/unit_36_thiere_is_are.html");
        }else if (intExtra == 34) {
            webView.loadUrl("file:///android_asset/player/unit_37_there_was_were.html");
        }else if (intExtra == 35) {
            webView.loadUrl("file:///android_asset/player/unit_38_it.html");
        }else if (intExtra == 36) {
            webView.loadUrl("file:///android_asset/player/unit_39_am_is_are_review.html");
        }else if (intExtra == 37) {
            webView.loadUrl("file:///android_asset/player/unit_40_have_you_are_you.html");
        }else if (intExtra == 38) {
            webView.loadUrl("file:///android_asset/player/unit_41_so_do_i_so_am_i.html");
        }else if (intExtra == 39) {
            webView.loadUrl("file:///android_asset/player/unit_44_who_saw_you.html");
        }else if (intExtra == 40) {
            webView.loadUrl("file:///android_asset/player/unit_45_who_is_she-talking_to.html");
        }else if (intExtra == 41) {
            webView.loadUrl("file:///android_asset/player/unit_46_how_what_which.html");
        }else if (intExtra == 42) {
            webView.loadUrl("file:///android_asset/player/unit_47_how_long_does_it_take.html");
        }else if (intExtra == 43) {
            webView.loadUrl("file:///android_asset/player/unit_48_clause_as_an_object.html");
        }else if (intExtra == 44) {
            webView.loadUrl("file:///android_asset/player/unit_49_she_said_that_reported_speech.html");
        }else if (intExtra == 45) {
            webView.loadUrl("file:///android_asset/player/unit_51_verb_n_to_ing.html");
        }else if (intExtra == 46) {
            webView.loadUrl("file:///android_asset/player/unit_52_I_want_you_to.html");
        }else if (intExtra == 47) {
            webView.loadUrl("file:///android_asset/player/unit_53_i_went_to_the_shop_to.html");
        }else if (intExtra == 48) {
            webView.loadUrl("file:///android_asset/player/unit_54_gon_to.html");
        }else if (intExtra == 49) {
            webView.loadUrl("file:///android_asset/player/unit_55_get.html");
        }else if (intExtra == 50) {
            webView.loadUrl("file:///android_asset/player/unit_56_make_n_do.html");
        }else if (intExtra == 51) {
            webView.loadUrl("file:///android_asset/player/unit_57_have_have_got.html");
        }else if (intExtra == 52) {
            webView.loadUrl("file:///android_asset/player/unit_58_subject_n_object_pronouns.html");
        }else if (intExtra == 53) {
            webView.loadUrl("file:///android_asset/player/unit_59_my_his_her_his.htm");
        }else if (intExtra == 54) {
            webView.loadUrl("file:///android_asset/player/unit_60_mine_hers_ours_yours.html");
        }else if (intExtra == 55) {
            webView.loadUrl("file:///android_asset/player/unit_61_I_me_my_mine.html");
        }else if (intExtra == 56) {
            webView.loadUrl("file:///android_asset/player/unit_62_myself_yourself_etc.html");
        }else if (intExtra == 57) {
            webView.loadUrl("file:///android_asset/player/unit_63_apotrophy_s.html");
        }else if (intExtra == 58) {
            webView.loadUrl("file:///android_asset/player/unit_64_a_an.html");
        }else if (intExtra == 59) {
            webView.loadUrl("file:///android_asset/player/unit_65_singular_and_plural.html");
        }else if (intExtra == 60) {
            webView.loadUrl("file:///android_asset/player/unit_66_countable_and_uncountable_nouns.html");
        }else if (intExtra == 61) {
            webView.loadUrl("file:///android_asset/player/unit_67_a_car_some_cars.html");
        }else if (intExtra == 62) {
            webView.loadUrl("file:///android_asset/player/unit_68_a_an_the.html");
        }else if (intExtra == 63) {
            webView.loadUrl("file:///android_asset/player/unit_69_the_usage.html");
        }else if (intExtra == 64) {
            webView.loadUrl("file:///android_asset/player/unit_70_go_n_the.html");
        }else if (intExtra == 65) {
            webView.loadUrl("file:///android_asset/player/unit_71_i_like_music_i_hate_exam.html");
        }else if (intExtra == 66) {
            webView.loadUrl("file:///android_asset/player/unit_72_the_n_places.html");
        }else if (intExtra == 67) {
            webView.loadUrl("file:///android_asset/player/unit_73_this_that_those.html");
        }else if (intExtra == 68) {
            webView.loadUrl("file:///android_asset/player/unit_74_one_ones.html");
        }else if (intExtra == 69) {
            webView.loadUrl("file:///android_asset/player/unit_75_some_n_any.html");
        }else if (intExtra == 70) {
            webView.loadUrl("file:///android_asset/player/unit_76_no_one_not_any.html");
        }else if (intExtra == 71) {
            webView.loadUrl("file:///android_asset/player/unit_77_not_anybody_anyone.html");
        }else if (intExtra == 72) {
            webView.loadUrl("file:///android_asset/player/unit_78_somebody_anybody.htm");
        }


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {

        if (mInterstitialAd !=null) {
            mInterstitialAd.show(this);
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                @Override
                public void onAdDismissedFullScreenContent() {
                    onBackPressed();
                    // Called when fullscreen content is dismissed.
                }
                @Override
                public void onAdShowedFullScreenContent() {
                    // Called when fullscreen content is shown.
                    // Make sure to set your reference to null so you don't
                    // show it a second time.
                    mInterstitialAd = null;
                }
            });

        }
        super.onBackPressed();
    }


    public void onDestroy() {
        try {
            this.webView.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    private void loadBanner() {
        adView.setAdUnitId(AD_UNIT_ID);

        AdSize adSize = getAdSize();
        adView.setAdSize(adSize);

        // Create an ad request. Check your logcat output for the hashed device ID
        // to get test ads on a physical device, e.g.,
        // "Use AdRequest.Builder.addTestDevice("ABCDE0123") to get test ads on this
        // device."
        AdRequest adRequest = new AdRequest.Builder().build();

        // Start loading the ad in the background.
        adView.loadAd(adRequest);
    }

    // Determine the screen width (less decorations) to use for the ad width.
    private AdSize getAdSize() {
        WindowMetrics windowMetrics = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            windowMetrics = getWindowManager().getCurrentWindowMetrics();
        }
        Rect bounds = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            bounds = windowMetrics.getBounds();
        }

        float adWidthPixels = adContainerView.getWidth();

        // If the ad hasn't been laid out, default to the full screen width.
        if (adWidthPixels == 0f) {
            adWidthPixels = bounds.width();
        }

        float density = getResources().getDisplayMetrics().density;
        int adWidth = (int) (adWidthPixels / density);

        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth);
    }
}
