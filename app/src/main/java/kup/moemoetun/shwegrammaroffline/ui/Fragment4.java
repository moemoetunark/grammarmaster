package kup.moemoetun.shwegrammaroffline.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import kup.moemoetun.shwegrammaroffline.constant.AppConstant;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kup.moemoetun.shwegrammaroffline.R;
import kup.moemoetun.shwegrammaroffline.adapter.MyRecyclerViewAdapter;
import kup.moemoetun.shwegrammaroffline.webview.Level_Two_WebView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class Fragment4 extends Fragment implements MyRecyclerViewAdapter.ItemClickListener {

    MyRecyclerViewAdapter adapter;
    private int itemPosition;
    private InterstitialAd mInterstitialAd;
    private int clickCount = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_level4, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadInterstitialAd();
        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("Getting Ready for the work");
        animalNames.add("Going to sleep");
        animalNames.add("Walking the dog");
        animalNames.add("Lemonade on a hot day");
        animalNames.add("Coffee on a cold night");
        animalNames.add("Pick up little sister");
        animalNames.add("Jim walked Nancy Home");
        animalNames.add("Making a sandwich");
        animalNames.add("John loves to read books");
        animalNames.add("Sam loves watching TV");

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MyRecyclerViewAdapter(getContext(), animalNames);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    private void loadInterstitialAd() {
        if (!AppConstant.isAdOn) return;
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(
                requireContext(),
                AppConstant.OFFLINE_INTERSTITIALS,
                adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        mInterstitialAd = null;
                    }
                }
        );
    }

    private void showInterstitialAd() {
        if (mInterstitialAd != null) {
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    mInterstitialAd = null;
                    loadInterstitialAd();
                    openNextScreen();
                }

                @Override
                public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {
                    mInterstitialAd = null;
                    openNextScreen();
                }
            });
            mInterstitialAd.show(requireActivity());
        } else {
            openNextScreen();
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        itemPosition = position;
        clickCount++;
        if (clickCount == 1 || clickCount >= AppConstant.adFrequency) {
            clickCount = 0;
            showInterstitialAd();
        } else {
            openNextScreen();
        }
    }

    private void openNextScreen() {
        Intent intent = new Intent(requireContext(), Level_Two_WebView.class);
        intent.putExtra("key", itemPosition);
        startActivity(intent);
    }
}