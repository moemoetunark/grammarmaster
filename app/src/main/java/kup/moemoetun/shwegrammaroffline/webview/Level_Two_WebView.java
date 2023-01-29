package kup.moemoetun.shwegrammaroffline.webview;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.view.WindowMetrics;
import android.webkit.WebView;
import android.widget.FrameLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import kup.moemoetun.shwegrammaroffline.R;

public class Level_Two_WebView extends AppCompatActivity {

    public WebView webView;
    private static final String AD_UNIT_ID = "ca-app-pub-4137439985376631/4189931024";
    private AdView adView;
    private FrameLayout adContainerView;
    private boolean initialLayoutComplete = false;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar_web_view);

        MobileAds.initialize(this, initializationStatus -> {
        });
        AdRequest adRequest = new AdRequest.Builder().build();
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
        //mAdView = findViewById(R.id.adView);
        //mAdView.loadAd(adRequest);

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

        webView = (WebView)findViewById(R.id.webView);
        webView = (WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        int intExtra = getIntent().getIntExtra("key",0);

        if (intExtra == 0) {
            webView.loadUrl("file:///android_asset/level2/1-getting-ready-for-work.html");
        } else if (intExtra == 1) {
            webView.loadUrl("file:///android_asset/level2/2-going-to-sleep.html");
        }else if (intExtra == 2) {
            webView.loadUrl("file:///android_asset/level2/3-walking-the-dog.html");
        }else if (intExtra == 3) {
            webView.loadUrl("file:///android_asset/level2/4-lemonade-on-hot-day.html");
        }else if (intExtra == 4) {
            webView.loadUrl("file:///android_asset/level2/5-coffee-on-a-cold-night.html");
        }else if (intExtra == 5) {
            webView.loadUrl("file:///android_asset/level2/6-pick-up-little-sister.html");
        }else if (intExtra == 6) {
            webView.loadUrl("file:///android_asset/level2/7-Jim-walked-Nancy-Home.html");
        }else if (intExtra == 7) {
            webView.loadUrl("file:///android_asset/level2/8-making-a-sandwich.html");
        }else if (intExtra == 8) {
            webView.loadUrl("file:///android_asset/level2/9-john-love-to-read-books.html");
        }else if (intExtra == 9) {
            webView.loadUrl("file:///android_asset/level2/10-sam-loves-watching-television.html");
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
