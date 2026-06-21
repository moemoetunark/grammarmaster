package kup.moemoetun.shwegrammaroffline.utility;


import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class AdMobHelper {

    private InterstitialAd interstitialAd;
    private final Context context;
    private final String adUnitId;

    public AdMobHelper(Context context, String adUnitId) {
        this.context = context;
        this.adUnitId = adUnitId;
        MobileAds.initialize(context);
        loadInterstitialAd(); // preload immediately
    }

    // Load Interstitial Ad
    public void loadInterstitialAd() {
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(
                context,
                adUnitId,
                adRequest,
                new InterstitialAdLoadCallback() {

                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd ad) {
                        interstitialAd = ad;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError error) {
                        interstitialAd = null;
                    }
                }
        );
    }

    // Show Interstitial Ad with callback
    public void showInterstitialAd(Runnable onAdClosed) {

        if (interstitialAd != null) {

            interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {

                @Override
                public void onAdDismissedFullScreenContent() {
                    interstitialAd = null;
                    loadInterstitialAd(); // preload next ad
                    if (onAdClosed != null) onAdClosed.run();
                }

                @Override
                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                    if (onAdClosed != null) onAdClosed.run();
                }
            });

            interstitialAd.show((Activity) context);

        } else {
            // If ad not ready → continue immediately
            if (onAdClosed != null) onAdClosed.run();
            loadInterstitialAd();
        }
    }
}
